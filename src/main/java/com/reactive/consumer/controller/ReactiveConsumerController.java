package com.reactive.consumer.controller;

import com.reactive.consumer.service.ReactiveConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
public class ReactiveConsumerController {
    ReactiveConsumerService reactiveConsumerService;
    @Autowired
    public  ReactiveConsumerController(ReactiveConsumerService reactiveConsumerService){
        this.reactiveConsumerService=reactiveConsumerService;
    }
    /*@GetMapping("/pull")
    public Flux<Object> subscribeMessages() {
        return reactiveConsumerService.consumeMessages();
    }*/
    /*@GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamMessages() {
        Flux<String> fluxMsg=reactiveConsumerService.consumeMessages();
        return fluxMsg;
    }*/
}
