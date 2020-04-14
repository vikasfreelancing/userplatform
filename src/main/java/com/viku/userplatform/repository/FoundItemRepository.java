package com.viku.userplatform.repository;

import com.viku.userplatform.dao.FoundItem;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface FoundItemRepository extends MongoRepository<FoundItem,Long> {
    FoundItem save(FoundItem foundItem);
    List<FoundItem> findAll();
    Optional<FoundItem> findById(Long id);
}
