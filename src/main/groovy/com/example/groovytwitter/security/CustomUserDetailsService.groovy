package com.example.groovytwitter.security

import com.example.groovytwitter.model.User
import com.example.groovytwitter.service.UserService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import org.springframework.security.core.userdetails.User.UserBuilder

import static org.springframework.security.core.userdetails.User.withUsername

@Component
class CustomUserDetailsService implements UserDetailsService {
    UserService userService

    CustomUserDetailsService(UserService userService) {
        this.userService = userService
    }

    @Override
    UserDetails loadUserByUsername(String login) {
        User user = userService.getByLogin(login)
        UserBuilder userDetailsBuilder = withUsername(user.getLogin())
        String[] roles = user.getRoles().stream()
                .map(role -> role.getRoleName().toString())
                .toArray(String[]::new)
        return userDetailsBuilder.password(user.getPassword())
                .roles(roles)
                .build()
    }
}
