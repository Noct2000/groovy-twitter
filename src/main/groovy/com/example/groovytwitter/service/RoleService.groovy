package com.example.groovytwitter.service

import com.example.groovytwitter.model.Role
import com.example.groovytwitter.model.Role.RoleName

interface RoleService {
    Role save(Role role)

    Role getByRoleName(RoleName roleName)

    Role getById(String id)
}
