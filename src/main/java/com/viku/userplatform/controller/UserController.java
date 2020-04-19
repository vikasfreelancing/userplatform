package com.viku.userplatform.controller;

import com.viku.userplatform.dto.User;
import com.viku.userplatform.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @PostMapping("/register")
    User registerUser(@RequestBody User user, HttpServletResponse response){
        log.info("Request : {}",user);
        Optional<User> checkIfExist = userRepository.findUserByEmail(user.getEmail());
        if(checkIfExist.isPresent()){
            response.setStatus(441);
            return null ;
        }
        User savedUser=userRepository.save(user);
        log.info("Response : {}",savedUser);
        return savedUser;
    }
    @GetMapping("/health")
    String health(){
        return "I am userPlatform up and running";
    }
    @PostMapping("/login")
    User loginUser(@RequestBody Map<String,String> login){
        log.info("Request : {}",login);
        User user =userRepository.findUserByEmailAndPassword(
                login.get("email"),login.get("password"));
        log.info("Response : {}",user);
        return user;
    }
}
