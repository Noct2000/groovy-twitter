package com.example.groovytwitter.mapper

import com.example.groovytwitter.model.Role
import com.example.groovytwitter.model.User
import com.example.groovytwitter.model.dto.UserCreateRequestDto
import com.example.groovytwitter.model.dto.UserResponseDto
import com.example.groovytwitter.model.dto.UserUpdateRequestDto
import com.example.groovytwitter.service.RoleService
import com.example.groovytwitter.service.UserService
import org.springframework.stereotype.Component

import java.util.stream.Collectors

@Component
class UserMapper {
    RoleService roleService

    UserService userService

    UserMapper(RoleService roleService, UserService userService) {
        this.roleService = roleService
        this.userService = userService
    }

    User toModel(UserCreateRequestDto userCreateRequestDto) {
        User user = new User()
        user.setLogin(userCreateRequestDto.getLogin())
        user.setPassword(userCreateRequestDto.getPassword())
        user.setRoles(Set.of(roleService.getByRoleName(Role.RoleName.USER)))
        return user
    }

    User toModel(UserUpdateRequestDto userUpdateRequestDto) {
        User user = userService.getByLogin(userUpdateRequestDto.getLogin())
        user.setPassword(userUpdateRequestDto.getPassword())
        user.setLogin(userUpdateRequestDto.getLogin())
        Set<Role> roles = userUpdateRequestDto.getRoleNames()
                .stream()
                .map(role -> roleService.getByRoleName(Role.RoleName.valueOf(role)))
                .collect(Collectors.toSet())
        user.setRoles(roles)
        return user
    }

    UserResponseDto toResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto()
        userResponseDto.setLogin(user.getLogin())
        userResponseDto.setId(user.getId())
        List<String> roles = user.getRoles()
                .stream()
                .map(role -> role.getRoleName().toString())
                .toList()
        userResponseDto.setRoles(roles)
        return userResponseDto
    }
}
