package com.viku.userplatform.controller;

import com.viku.userplatform.dto.User;
import com.viku.userplatform.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @PostMapping("/register")
    User registerUser(@RequestBody User user){
        log.info("Request : {}",user);
        User savedUser=userRepository.save(user);
        return savedUser;
    }
    @PostMapping("/health")
    String health(){
        return "I will register in future";
    }

    @PostMapping("/login")
    User loginUser(@RequestBody Map<String,String> login){
        log.info("Request : {}",login);
        User user=userRepository.findUserByEmailAndPassword(
                login.get("email"),login.get("password"));
        log.info("Response : {}",user);
        return user;
    }
}
