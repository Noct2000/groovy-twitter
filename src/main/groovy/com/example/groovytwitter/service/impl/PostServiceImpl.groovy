package com.example.groovytwitter.service.impl

import com.example.groovytwitter.model.Comment
import com.example.groovytwitter.model.Like
import com.example.groovytwitter.model.Post
import com.example.groovytwitter.model.User
import com.example.groovytwitter.repository.PostRepository
import com.example.groovytwitter.service.CommentService
import com.example.groovytwitter.service.LikeService
import com.example.groovytwitter.service.PostService
import com.example.groovytwitter.service.UserService
import org.springframework.stereotype.Service

@Service
class PostServiceImpl implements PostService {
    PostRepository postRepository
    CommentService commentService
    LikeService likeService
    UserService userService


    PostServiceImpl(
            PostRepository postRepository,
            CommentService commentService,
            LikeService likeService,
            UserService userService
    ) {
        this.postRepository = postRepository
        this.commentService = commentService
        this.likeService = likeService
        this.userService = userService
    }

    @Override
    Post save(Post post) {
        return postRepository.save(post)
    }

    @Override
    Post update(Post post, String login) {
        Post oldPost = getPost(post.getId())
        if (checkAbilityToChangePost(oldPost, login)) {
            return postRepository.save(post)
        }
        throw new RuntimeException("No ability to change post with id: " + post.getId())
    }

    @Override
    void removeById(String id, String login) {
        Post post = getPost(id)
        if (checkAbilityToChangePost(post, login)) {
            post.getLikes()
                    .forEach(like -> likeService.removeById(like.getId()))
            post.getComments().forEach(comment -> commentService.deleteCommentById(comment.getId()))
            postRepository.deleteById(id)
            return
        }
        throw new RuntimeException("No ability to remove post with id: " + post.getId())
    }

    @Override
    Post setLikeToPostById(String postId, String login) {
        Like like = new Like()
        like.setUser(userService.getByLogin(login))
        likeService.save(like)
        Post post = getPost(postId)
        if (post.getLikes() != null) {
            post.getLikes().add(like)
        } else {
            post.setLikes(Set.of(like))
        }
        return postRepository.save(post)
    }

    @Override
    Post unsetLikeToPostById(String postId, String login) {
        Post post = getPost(postId)
        Like like = post.getLikes()
                .stream()
                .filter(like -> like.getUser().getLogin() == login)
                .peek(like -> likeService.removeById(like.getId()))
                .findAny()
                .orElseThrow(() -> new RuntimeException(String.format("No like from \"%s\" for post with id: %s", login, post.getId())))
        post.getLikes().remove(like)
        return postRepository.save(post)
    }

    @Override
    Post commentPostById(String postId, Comment comment) {
        commentService.save(comment)
        Post post = getPost(postId)
        if (post.getComments() != null) {
            post.getComments().add(comment)
        } else {
            post.setComments(List.of(comment))
        }
        return postRepository.save(post)
    }

    @Override
    List<Post> getAllByUserLogin(String login) {
        User user = userService.getByLogin(login)
        return postRepository.findAllByAuthorId(user.getId())
    }

    @Override
    List<Post> getAll() {
        return postRepository.findAll()
    }

    @Override
    Post getById(String id) {
        return getPost(id)
    }

    private Post getPost(String postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("No post with id: " + postId))
    }

    private boolean checkAbilityToChangePost(Post post, String login) {
        return post.getAuthor().getLogin() == login
    }
}
