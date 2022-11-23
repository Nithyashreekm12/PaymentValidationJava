package com.example.demo.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.regex.Pattern;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.example.demo.entity.DestinationDetailedMessage;
import com.google.gson.Gson;

import ch.qos.logback.core.boolex.Matcher;

@Service
public class Consumer {

    @Autowired
    ValidateDate validateDate;
    String regex = "^\\d{10}$";

    final KafkaProperties kafkaProperties;
    Logger logger = LoggerFactory.getLogger(Consumer.class);

    public Consumer(KafkaProperties kafkaProperties) {

        this.kafkaProperties = kafkaProperties;
    }

    @KafkaListener(topics = "des-message", groupId = "group_id")
    public void consume(ConsumerRecord<String, String> paymentConsumer) {
        // logger.info("Received Records:" + paymentConsumer.value().toString());
        Gson gsonObj = new Gson();
        DestinationDetailedMessage d = gsonObj.fromJson(paymentConsumer.value(), DestinationDetailedMessage.class);
        logger.info("Destination message: " + d);
        logger.info("CPR::" + d.getCPR());
        try {
            if (d.getCPR() != null) {
                Pattern pattern = Pattern.compile(regex);
                java.util.regex.Matcher matcher = pattern.matcher(d.getCPR());
                {
                    if (matcher.matches() == true) {
                        System.out.println("Valid CPR number");
                        // System.exit(1);
                    } else {
                        System.out.println("Invalid CPR number");
                    }
                }
            }
          

        } catch (Exception e) {
            e.getMessage();
        }

        
    }
}
