package com.example.groovytwitter.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {
    PasswordEncoder passwordEncoder
    UserDetailsService userDetailsService

    SecurityConfig(
            PasswordEncoder passwordEncoder,
            UserDetailsService userDetailsService
    ) {
        this.passwordEncoder = passwordEncoder
        this.userDetailsService = userDetailsService
    }

    @SuppressWarnings('SpringJavaInjectionPointsAutowiringInspection')
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) {
        return http
        .cors(AbstractHttpConfigurer::disable)
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeRequests(
                auth -> auth
                .requestMatchers(HttpMethod.POST, "/users").permitAll()
                .requestMatchers(HttpMethod.GET, "/users/**").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.GET, "/inject").permitAll()
                .requestMatchers(HttpMethod.GET, "/success").permitAll()
                .requestMatchers(HttpMethod.PUT, "/users").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
                .requestMatchers("/posts/**").hasAnyRole("ADMIN", "USER")

        )
        .httpBasic(Customizer.withDefaults())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .userDetailsService(userDetailsService)
        .build()
    }
}
