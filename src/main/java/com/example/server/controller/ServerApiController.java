package com.example.server.controller;

import com.example.server.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class ServerApiController {

    @GetMapping("/hello")
    public User hello(@RequestParam String name, @RequestParam int age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }

    @PostMapping("/user/{userAge}/name/{userName}")
    public User post(@RequestBody User user, @PathVariable int userAge, @PathVariable String userName) {
        log.info("userAge : {}, userName : {}", userAge, userName);
        log.info("client req : {}", user);

        return user;

    }
}
