package com.viku.userplatform.repository;
import com.viku.userplatform.dao.LostItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LostItemRepository extends MongoRepository<LostItem, Long> {
    LostItem save(LostItem item);
    List<LostItem> findAll();
    Optional<LostItem> findById(Long id);
}
