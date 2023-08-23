package com.example.groovytwitter.model.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

class UserCreateRequestDto {
    @Size(min = 4)
    @NotBlank
    String login
    @Size(min = 8)
    @NotBlank
    String password

    String getLogin() {
        return login
    }

    void setLogin(String login) {
        this.login = login
    }

    String getPassword() {
        return password
    }

    void setPassword(String password) {
        this.password = password
    }
}
