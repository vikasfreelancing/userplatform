package com.viku.userplatform.service;

import com.viku.userplatform.dao.FoundItem;
import com.viku.userplatform.dao.LostItem;
import com.viku.userplatform.repository.FoundItemRepository;
import com.viku.userplatform.repository.LostItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemPullService {
    @Autowired
    private LostItemRepository lostItemRepository;
    @Autowired
    private FoundItemRepository foundItemRepository;

    public List<LostItem> getLostItems() {
        return lostItemRepository.findAll();
    }
    public List<FoundItem> getFoundItems() {
        return foundItemRepository.findAll();
    }
    public  FoundItem getFoundItem(Long id){
        Optional<FoundItem> foundItem = foundItemRepository.findById(id);
        return  foundItem.get();
    }
    public LostItem getLostItem(Long id){
       return lostItemRepository.findById(id).get();
    }

}
