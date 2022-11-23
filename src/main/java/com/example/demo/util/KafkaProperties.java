package com.example.demo.util;

import lombok.*;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.diagnostics.LoggingFailureAnalysisReporter;
import org.springframework.core.io.Resource;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerde;

import com.example.demo.entity.DestinationDetailedMessage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;



@ConfigurationProperties(prefix = "kafka", ignoreUnknownFields = true)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class KafkaProperties {

    Resource trustStoreLocation;
    Resource keyStoreLocation;
    String keyStorePassword;
    String trustStorePassword;
    String securityProtocol;
    String groupId;
    String bootstrapServers;
    String autoOffsetResetConfig;
    String fileMetadataTopic;
    String validateMetadata;
    String loggerTopic;
    String addBankTopic;
    String autoOffsetResetForStreams;
    String applicationId;
    String acks;
//    String transactionId;

    public Map<String, Object> toMap() throws IOException {

        Map<String, Object> configProps = new HashMap<>();
        configProps.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(CommonClientConfigs.GROUP_ID_CONFIG, groupId);

        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configProps.put(ProducerConfig.ACKS_CONFIG, acks);
        configProps.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, 100000000);
        configProps.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 1);
        configProps.put(JsonDeserializer.USE_TYPE_INFO_HEADERS,false);
        configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        configProps.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, LoggingFailureAnalysisReporter.class.getName());

     
        return configProps;
    }

    

    public Map<String, Object> consumerConfigsLocal() throws IOException {
        Map<String, Object> configProps = new HashMap<>();

        configProps.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(CommonClientConfigs.GROUP_ID_CONFIG, groupId);
        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetResetConfig);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(JsonDeserializer.USE_TYPE_INFO_HEADERS,false);
        configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        configProps.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, LoggingFailureAnalysisReporter.class.getName());
       

        return configProps;
    }

    public Map<String, Object> producerConfigsLocal() throws IOException {

        UUID uid =UUID.randomUUID();
        System.out.println("UUID: " +uid);
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(CommonClientConfigs.GROUP_ID_CONFIG, groupId);

        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, new JsonSerde<>(DestinationDetailedMessage.class));
        configProps.put(ProducerConfig.ACKS_CONFIG, acks);
        configProps.put(JsonDeserializer.USE_TYPE_INFO_HEADERS,false);
        configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        configProps.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, LoggingFailureAnalysisReporter.class.getName());

       
        return configProps;
    }
}




    

