package com.springboot.kafkaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestProducerController {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public TestProducerController(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/publish-to-kafka")
    public ResponseEntity<String> publishToKafka(@RequestBody String message) {
        try {
            // Kafka 채널에 메시지 발행
            kafkaTemplate.send("kafka-api", message);

            // 성공적으로 발행되었음을 클라이언트에 응답
            return ResponseEntity.ok("Message published to Kafka: " + message);
        } catch (Exception e) {
            // 발행 중에 오류가 발생한 경우 에러 응답
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error publishing message to Kafka: " + e.getMessage());
        }
    }
}
