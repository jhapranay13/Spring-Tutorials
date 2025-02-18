package com.example.kafka_spring.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class KafkaProducer {

    private KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(String message) {
        /*
kafkaTemplate.send("events", message).get(); is the synchronous call
this returns SendResult

We can Also Use ProducerRecord class to send the Message
We can also add Header Values

         */
        CompletableFuture<SendResult<String, String>> completableFuture = kafkaTemplate.send("events", message);
        completableFuture.whenComplete((sendResult, throwable) -> {

            if (throwable != null) {
                log.info("ERROR");
            } else {
                log.info("Success");
            }
        });
        log.info("Message logged {}", message);
    }
}
