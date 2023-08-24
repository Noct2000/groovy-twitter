package com.example.groovytwitter.service

import com.example.groovytwitter.model.User

interface UserService {
    User getById(String id)

    List<User> getAll()

    Boolean removeById(String id)

    User save(User user)

    User update(User user)

    User getByLogin(String login)
}
