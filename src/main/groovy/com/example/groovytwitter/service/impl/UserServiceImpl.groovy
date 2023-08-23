package com.example.groovytwitter.service.impl

import com.example.groovytwitter.exception.UserValidationException
import com.example.groovytwitter.model.User
import com.example.groovytwitter.repository.UserRepository
import com.example.groovytwitter.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl implements UserService {
    UserRepository userRepository

    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository
    }

    @Override
    User getById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserValidationException("No user with id: " + id))
    }

    @Override
    List<User> getAll() {
        return userRepository.findAll()
    }

    @Override
    Boolean removeById(String id) {
        Optional<User> user = userRepository.findById(id)
        user.ifPresent(userRepository::deleteById)
        return user.isPresent()
    }

    @Override
    User save(User user) {
        if (!isExists(user)) {
            return userRepository.save(user)
        }
        throw new UserValidationException("User by login: " + user.getLogin() + " already exists")
    }

    @Override
    User update(User user) {
        if (isExists(user)) {
            return userRepository.save(user)
        }
        throw new UserValidationException("No user for update with login: " + user.login)
    }

    private boolean isExists(User user) {
        Optional<User> optionalUser = userRepository.findByLogin(user.login)
        return optionalUser.isPresent()
    }

}
