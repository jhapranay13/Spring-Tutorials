package com.example.kafka_spring.activity;

import com.example.kafka_spring.producer.KafkaProducer;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class MessageActivity {

    private KafkaProducer producer;

    public MessageActivity(KafkaProducer producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/message", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> publish(@RequestBody String message) {
        producer.sendMessage(message);
        return ResponseEntity.ok("Message Sent");
    }
}
