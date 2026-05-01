package com.billing.auth.service;


import com.billing.auth.dto.*;
import com.billing.auth.entity.User;
import com.billing.auth.repository.*;
import com.billing.auth.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public AuthResponse register(RegisterRequest request) {

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .role(roleRepo.findById(7L).orElse(null)) // default role
                .build();

        userRepo.save(user);

        String token = jwtService.generateToken(user.getEmail(),user.getRole().getName());

        return new AuthResponse(token);
    }

    public AuthResponse login(AuthRequest request) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepo.findByEmail(request.getEmail()).orElseThrow();

        String token = jwtService.generateToken(user.getEmail(),user.getRole().getName());

        return new AuthResponse(token);
    }
}
