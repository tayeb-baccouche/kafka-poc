package com.example.consumer.controller;

import com.example.consumer.service.ReactiveKafkaConsumerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class KafkaReactiveController {

    private final ReactiveKafkaConsumerService consumerService;

    public KafkaReactiveController(ReactiveKafkaConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamKafkaMessages() {
        return consumerService.getMessages();
    }
}
