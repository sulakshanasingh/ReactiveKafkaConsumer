package com.reactive.consumer;

import com.reactive.consumer.service.ReactiveConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsumerApplication implements CommandLineRunner {
	@Autowired
	ReactiveConsumerService reactiveConsumerService;

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		reactiveConsumerService.consumeMessages()
				.subscribe(s -> System.out.println("Consumer messages received ->"+s));
	}
}
