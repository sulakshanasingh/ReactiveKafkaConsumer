package com.reactive.consumer.service;

import com.reactive.consumer.config.RetryTopicKafkaTemplate;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.kafka.retrytopic.DltStrategy;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReactiveConsumerService {
    @Value(value = "${topic.dlq}")
    private String dlqTopic;
   private final List<FluxSink<String>> sinks = new ArrayList<>();
    public Flux<String> consumeMessages() {
        return Flux.create(sinks::add);
    }

    @KafkaListener(topics = "my-topic", groupId = "group_id")
    public void listen(String message, Acknowledgment acknowledgment) {
        sinks.forEach(sink -> {
            sink.next(message);
            acknowledgment.acknowledge();
        });
    }
    @DltHandler
    public void handleDltPayment(
            String message, @Header(KafkaHeaders.RECEIVED_TOPIC)String dlqTopic) {
        sinks.forEach(sink -> sink.next(message));
    }

}
