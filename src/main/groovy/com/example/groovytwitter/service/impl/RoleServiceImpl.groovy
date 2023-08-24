package com.example.groovytwitter.service.impl

import com.example.groovytwitter.model.Role
import com.example.groovytwitter.repository.RoleRepository
import com.example.groovytwitter.service.RoleService
import org.springframework.stereotype.Service

@Service
class RoleServiceImpl implements RoleService {
    RoleRepository roleRepository

    RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository
    }

    @Override
    Role save(Role role) {
        return roleRepository.save(role)
    }

    @Override
    Role getByRoleName(Role.RoleName roleName) {
        return roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new RuntimeException("No role with name: " + roleName))
    }

    @Override
    Role getById(String id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No role with id: " + id))
    }
}
