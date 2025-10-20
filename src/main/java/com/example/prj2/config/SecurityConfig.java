package com.example.prj2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // ❌ Tắt CSRF (cho phép Postman gửi request)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // ✅ Cho phép tất cả request
                )
                .formLogin(login -> login.disable()) // ❌ Tắt form login mặc định
                .httpBasic(basic -> basic.disable()); // ❌ Tắt Basic Auth

        return http.build();
    }
}
