package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class ProduceToLoggerTopic {
    @Autowired
        private KafkaTemplate<String,String> kafkaTemplate;
    
        public void send(String message ) {
            kafkaTemplate.send("add", message);
        }
    
}
