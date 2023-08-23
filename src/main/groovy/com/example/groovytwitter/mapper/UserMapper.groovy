package com.example.groovytwitter.mapper

import com.example.groovytwitter.model.User
import com.example.groovytwitter.model.dto.UserCreateRequestDto
import com.example.groovytwitter.model.dto.UserResponseDto
import com.example.groovytwitter.model.dto.UserUpdateRequestDto
import org.springframework.stereotype.Component

@Component
class UserMapper {
    User toModel(UserCreateRequestDto userCreateRequestDto) {
        User user = new User()
        user.setLogin(userCreateRequestDto.getLogin())
        user.setPassword(userCreateRequestDto.getPassword())
        return user
    }

    User toModel(UserUpdateRequestDto userUpdateRequestDto) {
        User user = new User()
        user.setId(userUpdateRequestDto.getId())
        user.setPassword(userUpdateRequestDto.getPassword())
        user.setLogin(userUpdateRequestDto.getLogin())
        return user
    }

    UserResponseDto toResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto()
        userResponseDto.setLogin(user.getLogin())
        userResponseDto.setId(user.getId())
        return userResponseDto
    }
}
