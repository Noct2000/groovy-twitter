package com.example.groovytwitter.model.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

class UserUpdateRequestDto {
    @Size(min = 4)
    @NotBlank
    String login
    @Size(min = 8)
    @NotBlank
    String password
    @NotNull
    List<String> roleNames
    @NotNull
    Set<String> subscriptionUserIds

    Set<String> getSubscriptionUserIds() {
        return subscriptionUserIds
    }

    void setSubscriptionUserIds(Set<String> subscriptionUserIds) {
        this.subscriptionUserIds = subscriptionUserIds
    }

    List<String> getRoleNames() {
        return roleNames
    }

    void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames
    }

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
