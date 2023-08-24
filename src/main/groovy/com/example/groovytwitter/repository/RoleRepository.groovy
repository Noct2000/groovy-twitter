package com.example.groovytwitter.repository

import com.example.groovytwitter.model.Role
import com.example.groovytwitter.model.Role.RoleName
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByRoleName(RoleName roleName)
}
