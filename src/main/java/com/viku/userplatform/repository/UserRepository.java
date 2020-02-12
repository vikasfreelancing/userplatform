package com.viku.userplatform.repository;

import com.viku.userplatform.dto.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
    User save(User user);
    User findUserByEmailAndPassword(String email,String password);
}

