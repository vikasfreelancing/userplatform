package com.viku.userplatform.service;

import com.viku.userplatform.dao.FoundItem;
import com.viku.userplatform.dao.LostItem;
import com.viku.userplatform.dto.LostItemDetails;
import com.viku.userplatform.dto.User;
import com.viku.userplatform.repository.FoundItemRepository;
import com.viku.userplatform.repository.LostItemRepository;
import com.viku.userplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemPullService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LostItemRepository lostItemRepository;
    @Autowired
    private FoundItemRepository foundItemRepository;

    public List<LostItem> getLostItems(String userId) {
        if(userId == null)
        return lostItemRepository.findAll();
        else return lostItemRepository.findByUserId(userId);
    }
    public List<FoundItem> getFoundItems(String userId) {
        if (userId == null)
        return foundItemRepository.findAll();
        else return foundItemRepository.findByUserId(userId);
    }
    public  FoundItem getFoundItem(String id){
        Optional<FoundItem> foundItem = foundItemRepository.findById(id);
        return  foundItem.get();
    }
    public LostItem getLostItem(String id){
       return lostItemRepository.findById(id).get();
    }

    public LostItemDetails getLostItemDetails(String itemId) {
        Optional<LostItem> lostItem = lostItemRepository.findById(itemId);
        if (! lostItem.isPresent()) return null;
        Optional<User> user = userRepository.findById(lostItem.get().getUserId());
        if (! user.isPresent()) return null;
        if(lostItem.get().isFound()){
          Optional<FoundItem> foundItem = foundItemRepository.findById(lostItem.get().getFoundId());
          return LostItemDetails.builder().lostItem(lostItem.get()).user(user.get()).foundItem(foundItem.get()).build();
        }
        else{
            return LostItemDetails.builder().lostItem(lostItem.get()).user(user.get()).foundItem(null).build();
        }
    }
}
