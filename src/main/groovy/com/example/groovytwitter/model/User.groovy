package com.example.groovytwitter.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document("user")
class User {
    @Id
    String id
    @Indexed(unique = true)
    String login
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
