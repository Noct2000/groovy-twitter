package com.example.groovytwitter.model.dto

import jakarta.validation.constraints.NotBlank

class SubscriptionRequestDto {
    @NotBlank
    String userId

    String getUserId() {
        return userId
    }

    void setUserId(String userId) {
        this.userId = userId
    }
}
