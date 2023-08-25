package com.example.groovytwitter.service.impl

import com.example.groovytwitter.exception.UserValidationException
import com.example.groovytwitter.model.User
import com.example.groovytwitter.repository.UserRepository
import com.example.groovytwitter.service.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class UserServiceImpl implements UserService {
    UserRepository userRepository

    PasswordEncoder passwordEncoder

    UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository
        this.passwordEncoder = passwordEncoder
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
            return saveUser(user)
        }
        throw new UserValidationException("User by login: " + user.getLogin() + " already exists")
    }

    @Override
    User update(User user) {
        if (isExists(user)) {
            return saveUser(user)
        }
        throw new UserValidationException("No user for update with login: " + user.login)
    }

    @Override
    User getByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(
                        () -> new RuntimeException("No user for update with login: " + user.login)
                )
    }

    @Override
    User subscribeUser(String currentUserLogin, String subscriptionUserId) {
        User currentUser = getByLogin(currentUserLogin)
        if (currentUser.getSubscriptions() != null) {
            currentUser.getSubscriptions().add(getById(subscriptionUserId))
        } else {
            currentUser.setSubscriptions(Set.of(getById(subscriptionUserId)))
        }
        return userRepository.save(currentUser)
    }

    @Override
    User unsubscribeUser(String currentUserLogin, String subscriptionUserId) {
        User currentUser = getByLogin(currentUserLogin)
        Set<User> subscriptions = currentUser.getSubscriptions()
                .stream()
                .filter(user -> user.getId() != subscriptionUserId)
                .collect(Collectors.toSet())
        currentUser.setSubscriptions(subscriptions)
        return userRepository.save(currentUser)
    }

    private boolean isExists(User user) {
        Optional<User> optionalUser = userRepository.findByLogin(user.login)
        return optionalUser.isPresent()
    }

    private User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()))
        return userRepository.save(user)
    }
}
