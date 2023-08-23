package com.example.groovytwitter.controller

import com.example.groovytwitter.mapper.UserMapper
import com.example.groovytwitter.model.User
import com.example.groovytwitter.model.dto.UserCreateRequestDto
import com.example.groovytwitter.model.dto.UserResponseDto
import com.example.groovytwitter.model.dto.UserUpdateRequestDto
import com.example.groovytwitter.service.UserService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController {
    UserService userService
    UserMapper userMapper

    UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService
        this.userMapper = userMapper
    }

    @GetMapping
    List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userMapper::toResponseDto)
                .toList()
    }

    @GetMapping("/{id}")
    UserResponseDto get(@PathVariable String id) {
        return userMapper.toResponseDto(userService.getById(id))
    }

    @PostMapping
    UserResponseDto add(@RequestBody @Valid UserCreateRequestDto userCreateRequestDto) {
        User user = userService.save(userMapper.toModel(userCreateRequestDto))
        return userMapper.toResponseDto(user)
    }

    @DeleteMapping("/{id}")
    boolean removeById(@PathVariable String id) {
        return userService.removeById(id)
    }

    @PutMapping
    UserResponseDto update(@RequestBody @Valid UserUpdateRequestDto userUpdateRequestDto) {
        User user = userService.update(userMapper.toModel(userUpdateRequestDto))
        return userMapper.toResponseDto(user)
    }
}
