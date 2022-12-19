package com.simple.producer;

import java.util.Random;
import java.util.UUID;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderManualProvider {

	private final StreamBridge streamBridge;

	@Scheduled(cron = "*/10 * * * * *")
	public void sendMessage() {

		Message<Order> message = MessageBuilder
				.withPayload(Order.builder()
						             .id(UUID.randomUUID())
						             .owner("gizem")
						             .outland(new Random().nextBoolean())
						           .build())
				.setHeader("partitionKey", "manual").build();

		log.info("New order is sent on demand...");
		streamBridge.send("sendOrder-out-0", message);
	}
}
