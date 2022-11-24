package com.simple.producer;

import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class OrderProvider {

	@Bean
	public Supplier<Message<Order>> sendOrder() {
		return () -> {
			log.info("New order is sent from provider...");
			
			Message<Order> message = MessageBuilder
					.withPayload(Order.builder()
							             .id(UUID.randomUUID())
							             .owner("gizem")
							             .outland(new Random().nextBoolean())
							           .build())
					.setHeader("partitionKey", "auto").build();
			return message;
		};
	}

}
