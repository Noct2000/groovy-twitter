package com.example.groovytwitter

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableMongoRepositories
class GroovyTwitterApplication {

    static void main(String[] args) {
        SpringApplication.run(GroovyTwitterApplication, args)
    }

}
