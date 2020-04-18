package com.viku.userplatform.repository;

import com.viku.userplatform.dto.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {
    User save(User user);
    User findUserByEmailAndPassword(String email,String password);
    Optional<User> findUserByEmail();
}

