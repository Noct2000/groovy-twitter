package com.example.groovytwitter.mapper

import com.example.groovytwitter.model.Role
import com.example.groovytwitter.model.User
import com.example.groovytwitter.model.dto.UserCreateRequestDto
import com.example.groovytwitter.model.dto.UserResponseDto
import com.example.groovytwitter.model.dto.UserUpdateRequestDto
import com.example.groovytwitter.service.RoleService
import org.springframework.stereotype.Component

import java.util.stream.Collectors

@Component
class UserMapper {
    RoleService roleService

    UserMapper(RoleService roleService) {
        this.roleService = roleService
    }

    User toModel(UserCreateRequestDto userCreateRequestDto) {
        User user = new User()
        user.setLogin(userCreateRequestDto.getLogin())
        user.setPassword(userCreateRequestDto.getPassword())
        user.setRoles(Set.of(roleService.getByRoleName(Role.RoleName.USER)))
        return user
    }

    User toModel(UserUpdateRequestDto userUpdateRequestDto) {
        User user = new User()
        user.setId(userUpdateRequestDto.getId())
        user.setPassword(userUpdateRequestDto.getPassword())
        user.setLogin(userUpdateRequestDto.getLogin())
        Set<Role> roles = userUpdateRequestDto.getRoleIds()
                .stream()
                .map(roleService::getById)
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
