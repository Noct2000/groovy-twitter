package com.example.groovytwitter.model.dto

class LikeResponseDto {
    String id
    UserResponseDto user

    String getId() {
        return id
    }

    void setId(String id) {
        this.id = id
    }

    UserResponseDto getUser() {
        return user
    }

    void setUser(UserResponseDto user) {
        this.user = user
    }
}
