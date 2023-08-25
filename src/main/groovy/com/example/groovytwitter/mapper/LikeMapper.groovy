package com.example.groovytwitter.mapper

import com.example.groovytwitter.model.Like
import com.example.groovytwitter.model.dto.LikeResponseDto
import org.springframework.stereotype.Component

@Component
class LikeMapper {
    UserMapper userMapper

    LikeMapper(UserMapper userMapper) {
        this.userMapper = userMapper
    }

    LikeResponseDto toLikeResponseDto(Like like) {
        LikeResponseDto likeResponseDto = new LikeResponseDto()
        likeResponseDto.setId(like.getId())
        likeResponseDto.setUser(userMapper.toResponseDto(like.getUser()))
        return likeResponseDto
    }
}
