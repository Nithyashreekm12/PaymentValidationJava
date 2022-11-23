package com.example.demo.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.diagnostics.LoggingFailureAnalysisReporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerde;

import com.example.demo.entity.DestinationDetailedMessage;

@Configuration
public class KafkaConfig {

    @Bean
    public ConsumerFactory<String, DestinationDetailedMessage> consumerFactory() throws IOException {
        Map<String, Object> configProps = new HashMap<>();

        configProps.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(CommonClientConfigs.GROUP_ID_CONFIG, "group_id");
        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
        configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        configProps.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS,
                LoggingFailureAnalysisReporter.class.getName());
      
        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, DestinationDetailedMessage> kafkaListenerContainerFactory()
            throws IOException {
        ConcurrentKafkaListenerContainerFactory<String, DestinationDetailedMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    // @Qualifier
    // @Bean("produceLogsToLoggerTopic")
    // public KafkaProducer<String, DestinationDetailedMessage>
    // produceLogsToLoggerTopic() throws IOException {
    // return new KafkaProducer(kafkaProperties.toMap(), new StringSerializer(), new
    // JsonSerde<DestinationDetailedMessage>().serializer());
    // }

    // @Bean("producerPayment")
    // public KafkaProducer<String, DestinationDetailedMessage> producerPayment()
    // throws IOException {
    // KafkaProducer<String, DestinationDetailedMessage> paymentProducer = new
    // KafkaProducer(kafkaProperties.producerConfigsLocal(), new StringSerializer(),
    // new JsonSerde<DestinationMessage>().serializer());
    // return paymentProducer;
    // }
}
