package com.billing.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity  // ✅ ADD HERE
@ComponentScan(basePackages = {
    "com.billing.auth",      // your auth module
    "com.billing.hostel"   // ← Add this line
    // "com.billing.shortcourse",
    // "com.billing.driving",
    // add other modules if any
})
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}