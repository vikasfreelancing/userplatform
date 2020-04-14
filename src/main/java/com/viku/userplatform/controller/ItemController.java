package com.viku.userplatform.controller;

import com.viku.userplatform.dao.FoundItem;
import com.viku.userplatform.dao.LostItem;
import com.viku.userplatform.service.ItemPullService;
import com.viku.userplatform.service.ItemPushService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
public class ItemController {

    @Autowired
    private ItemPushService itemPushService;
    @Autowired
    private ItemPullService itemPullService;

    @PostMapping("/lost/save")
    public LostItem saveLostItem(@RequestBody LostItem item) {
        log.info("Request received for saving item with request : {}", item);
        LostItem savedItem = itemPushService.saveitemAndPushTokafka(item);
        log.info("Lost Item : {} saved successfully ", savedItem);
        return savedItem;
    }

    @GetMapping("/lost/items")
    public List<LostItem> getLostItems() {
        log.info("Request received for get Items");
        List<LostItem> items = itemPullService.getLostItems();
        log.info("Returning following items : {}", items);
        return items;
    }
    @PostMapping("/found/save")
    public FoundItem saveFoundItem(@RequestBody FoundItem foundItem) throws IOException {
        log.info("Request received for saving found item with request : {}", foundItem);
        FoundItem savedItem = itemPushService.saveFoundAndPushTokafka(foundItem);
        log.info("foundItem : {} saved successfully ", savedItem);
        return savedItem;
    }

    @GetMapping("/found/items")
    public List<FoundItem> getFoundItems() {
        log.info("Request received for get found Items");
        List<FoundItem> items = itemPullService.getFoundItems();
        log.info("Returning following found items : {}", items);
        return items;
    }

}
