package com.viku.userplatform.controller;

import com.viku.userplatform.dto.ChatMapping;
import com.viku.userplatform.dto.User;
import com.viku.userplatform.repository.ChatMappingRepository;
import com.viku.userplatform.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/user")
@CrossOrigin(maxAge = 3600)
public class UserController {

    @Autowired
    private ChatMappingRepository chatMappingRepository;
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/register")
    public User registerUser(@RequestBody User user, HttpServletResponse response){
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
    public String health(){
        return "I am userPlatform up and running";
    }
    @PostMapping("/login")
    public User loginUser(@RequestBody Map<String,String> login){
        log.info("Request : {}",login);
        User user =userRepository.findUserByEmailAndPassword(
                login.get("email"),login.get("password"));
        log.info("Response : {}",user);
        return user;
    }
    @GetMapping("/all")
    public List<User> getAllUsers(@RequestParam(name="email") String email){
        List<User> users = userRepository.findAll();
        users.forEach((user -> {
         List<String> emails = Arrays.asList(user.getEmail(),email);
         log.info("Emails are : {}",emails);
         ChatMapping chatMapping = chatMappingRepository.findByFirstEmailInAndSecondEmailIn(
                 emails,emails);
         if(chatMapping == null ) user.setChatId(null);
         else user.setChatId(chatMapping.getId());
        }));
        return users;
    }

    @PostMapping("/createChatMapping")
    public ChatMapping createMapping(@RequestBody ChatMapping chatMapping){
        if(chatMapping.getFirstEmail().equalsIgnoreCase(chatMapping.getSecondEmail())){
            return null;
        }
        List<String> list = Arrays.asList(chatMapping.getFirstEmail(),chatMapping.getSecondEmail());
        ChatMapping mapping = chatMappingRepository.findByFirstEmailInAndSecondEmailIn(list,list);
        if(mapping!=null)
            return mapping;
        ChatMapping saved = chatMappingRepository.save(chatMapping);
        return saved;
    }

    @PostMapping("/saveProfileImage")
    public User saveProfileImage(@RequestBody Map<String,String> request){
        log.info("save profile image request request:{}",request);
        String email = request.get("email");
        String profileImage = request.get("profileImage");
        User user = userRepository.findUserByEmail(email).get();
        user.setProfileImage(profileImage);
        User savedUser = userRepository.save(user);
        log.info("Response user : {}",savedUser);
        return savedUser;
    }
}
