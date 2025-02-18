package com.example.kafka_spring.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = {"events"})
    public void onMessage(ConsumerRecord<String, String> consumerRecord) {
        log.info("Consumer {} ", consumerRecord);
    }
}
