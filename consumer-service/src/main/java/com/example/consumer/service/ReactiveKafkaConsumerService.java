package com.example.consumer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Service
public class ReactiveKafkaConsumerService {

    private final Sinks.Many<String> sink;

    public ReactiveKafkaConsumerService() {
        this.sink = Sinks.many().multicast().onBackpressureBuffer();
    }

    @KafkaListener(topics = "test-topic", groupId = "reactive-consumer-group")
    public void consume(String message) {
        System.out.println("Message reçu : " + message);
        sink.tryEmitNext(message);
    }

    public Flux<String> getMessages() {
        return sink.asFlux();
    }
}
