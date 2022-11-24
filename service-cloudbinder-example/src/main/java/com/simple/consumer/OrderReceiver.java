package com.simple.consumer;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class OrderReceiver {

	@Bean
	public Consumer<Message<Order>> receiveOrder() {
		return message -> log.info("Order received with id {} and type {}", message.getPayload().getId(),
				message.getHeaders().get("partitionKey"));
	}

}
