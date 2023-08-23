package com.example.groovytwitter.model.dto

class UserResponseDto {
    String id
    String login

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
}
