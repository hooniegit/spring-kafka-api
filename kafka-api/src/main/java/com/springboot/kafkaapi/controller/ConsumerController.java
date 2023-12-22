package com.springboot.kafkaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/read")
public class ConsumerController {

    private String receivedMessage;

    @KafkaListener(topics = "kafka-api", groupId = "group_1")
    public void listener(Object data) {
        System.out.println("Received message: " + data);
        this.receivedMessage = data.toString();
    }

    @GetMapping("/getMessage")
    public String getMessage() {
        return "Last received message: " + receivedMessage;
    }
}
