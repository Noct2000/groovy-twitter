package com.example.groovytwitter.model.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

class UserUpdateRequestDto {
    @NotBlank
    String id
    @Size(min = 4)
    @NotBlank
    String login
    @Size(min = 8)
    @NotBlank
    String password

    String getId() {
        return id
    }

    void setId(String id) {
        this.id = id
    }

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
