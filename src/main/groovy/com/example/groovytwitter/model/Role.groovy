package com.example.groovytwitter.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("role")
class Role {
    @Id
    String id
    RoleName roleName

    RoleName getRoleName() {
        return roleName
    }

    void setRoleName(RoleName roleName) {
        this.roleName = roleName
    }

    @Override
    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Role role = (Role) o

        if (roleName != role.roleName) return false

        return true
    }

    @Override
    int hashCode() {
        return (roleName != null ? roleName.hashCode() : 0)
    }

    enum RoleName {
        ADMIN, USER
    }
}
