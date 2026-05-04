package com.billing.auth.security;

import com.billing.auth.entity.Role;
import com.billing.auth.entity.Permission;
import com.billing.auth.repository.RoleRepository;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private final RoleRepository roleRepository;

    // ✅ inject repository
    public JwtService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // ✅ KEEPING YOUR METHOD SIGNATURE
    public String generateToken(String email, String roleName) {

        // 🔍 fetch role from DB
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        Map<String, Object> claims = new HashMap<>();

        // ✅ add role
        claims.put("role", role.getName());

        // ✅ add permissions
        List<String> permissions = role.getPermissions()
                .stream()
                .map(Permission::getName)
                .collect(Collectors.toList());

        claims.put("permissions", permissions);

        return Jwts.builder()
                .setClaims(claims)   // 🔥 IMPORTANT
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token, String email) {
        return extractUsername(token).equals(email);
    }
}
