package com.example.groovytwitter.model.dto

class UserResponseDto {
    String id
    String login
    List<String> roles
    Set<String> subscriptionUserIds

    Set<String> getSubscriptionUserIds() {
        return subscriptionUserIds
    }

    void setSubscriptionUserIds(Set<String> subscriptionUserIds) {
        this.subscriptionUserIds = subscriptionUserIds
    }

    List<String> getRoles() {
        return roles
    }

    void setRoles(List<String> roles) {
        this.roles = roles
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
}
