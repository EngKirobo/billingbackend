package com.billing.auth.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.Claims;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        String email = jwtService.extractUsername(token);
        Claims claims = jwtService.extractAllClaims(token);

        String role = claims.get("role", String.class);

        // ✅ FIX: extract permissions
        // List<String> permissions = claims.get("permissions", List.class);

        List<?> rawPermissions = claims.get("permissions", List.class);

        List<String> permissions = rawPermissions == null
                ? List.of()
                : rawPermissions.stream()
                    .map(Object::toString)
                    .toList();
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            var userDetails = userDetailsService.loadUserByUsername(email);

            if (jwtService.isTokenValid(token, userDetails.getUsername())) {

                // ✅ Build authorities list
                List<SimpleGrantedAuthority> authorities = new ArrayList<>();

                // 1. Add role (optional if you still use it)
                if (role != null) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
                }

                // 2. Add permissions (THIS IS THE FIX)
                if (permissions != null) {
                    authorities.addAll(
                            permissions.stream()
                                    .map(SimpleGrantedAuthority::new)
                                    .toList()
                    );
                }

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                authorities
                        );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}