package com.ua.rosella.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping("/demo")
    public ResponseEntity<String> sayHelloAuth() {
        return ResponseEntity.ok("It's work! Auth");
    }

    @GetMapping("/auth/demo")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("It's work!");
    }
}
