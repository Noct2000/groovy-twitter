package com.example.groovytwitter.controller

import com.example.groovytwitter.model.Role
import com.example.groovytwitter.model.User
import com.example.groovytwitter.service.RoleService
import com.example.groovytwitter.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class InjectBaseDataController {
    RoleService roleService
    UserService userService

    InjectBaseDataController(RoleService roleService, UserService userService) {
        this.roleService = roleService
        this.userService = userService
    }

    @GetMapping("/inject")
    String injectBaseData() {
        Role userRole = new Role()
        Role adminRole = new Role()
        userRole.setRoleName(Role.RoleName.USER)
        adminRole.setRoleName(Role.RoleName.ADMIN)
        roleService.save(adminRole)
        roleService.save(userRole)
        User admin = new User()
        admin.setLogin("admin")
        admin.setPassword("admin")
        admin.setRoles(Set.of(adminRole, userRole))
        userService.save(admin)
        return "data was successfully injected"
    }
}
