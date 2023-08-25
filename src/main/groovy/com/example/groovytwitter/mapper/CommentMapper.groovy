package com.example.groovytwitter.mapper

import com.example.groovytwitter.model.Comment
import com.example.groovytwitter.model.dto.CommentCreateRequestDto
import com.example.groovytwitter.model.dto.CommentResponseDto
import com.example.groovytwitter.service.UserService
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class CommentMapper {
    UserService userService
    UserMapper userMapper

    CommentMapper(
            UserService userService,
            UserMapper userMapper
    ) {
        this.userService = userService
        this.userMapper = userMapper
    }

    Comment toModel(
            CommentCreateRequestDto commentCreateRequestDto,
            String login
    ) {
        Comment comment = new Comment()
        comment.setTimestamp(LocalDateTime.now())
        comment.setText(commentCreateRequestDto.getText())
        comment.setAuthor(userService.getByLogin(login))
        return comment
    }

    CommentResponseDto toCommentResponseDto(Comment comment) {
        CommentResponseDto commentResponseDto = new CommentResponseDto()
        commentResponseDto.setId(comment.getId())
        commentResponseDto.setText(comment.getText())
        commentResponseDto.setAuthor(userMapper.toResponseDto(comment.getAuthor()))
        commentResponseDto.setTimestamp(comment.getTimestamp())
        return commentResponseDto
    }
}
