package com.example.groovytwitter.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController {
    @GetMapping("/success")
    String success() {
        return "success"
    }
}
