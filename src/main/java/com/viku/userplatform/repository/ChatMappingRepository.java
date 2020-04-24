package com.viku.userplatform.repository;

import com.viku.userplatform.dao.FoundItem;
import com.viku.userplatform.dto.ChatMapping;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ChatMappingRepository extends MongoRepository<ChatMapping,String> {
    ChatMapping findByFirstEmailInAndSecondEmailIn(List<String> firstEmail,List<String> secondEmail);
}
