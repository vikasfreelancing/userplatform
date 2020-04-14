package com.viku.userplatform.service;

import com.viku.userplatform.dao.FoundItem;
import com.viku.userplatform.dao.LostItem;
import com.viku.userplatform.kafka.Producer;
import com.viku.userplatform.repository.FoundItemRepository;
import com.viku.userplatform.repository.LostItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
public class ItemPushService {

    @Autowired
    private Producer kafkaPushService;
    @Autowired
    private LostItemRepository lostItemRepository;
    @Autowired
    private FoundItemRepository foundItemRepository;

    @Value("${cloudkarafka.lost_topic}")
    private String lostItemKafkaTopic;

    @Value("${cloudkarafka.found_topic}")
    private String foundItemKafkaTopic;

    @Transactional
    public LostItem saveitemAndPushTokafka(LostItem item){
        LostItem savedItem = lostItemRepository.save(item);
        kafkaPushService.send(savedItem.getId().toString(),lostItemKafkaTopic);
        return savedItem;
    }
    @Transactional
    public FoundItem saveFoundAndPushTokafka(FoundItem item) throws IOException {
        FoundItem savedItem = foundItemRepository.save(item);
        kafkaPushService.send(savedItem.getId()+"",foundItemKafkaTopic);
        return savedItem;
    }

    public LostItem saveLostItem(LostItem lostItem){
        return lostItemRepository.save(lostItem);
    }
}
