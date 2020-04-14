package com.viku.userplatform.kafka;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Component
@Slf4j
public class Consumer {
//    @KafkaListener(topics = "")
//    public void consumeanyMessage(String message,
//                                  @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
//                                  @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
//                                  @Header(KafkaHeaders.OFFSET) List<Long> offsets) throws Exception {
//
//    }
}
