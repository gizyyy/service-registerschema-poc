package com.simple.producer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

import org.springframework.cloud.function.cloudevent.CloudEventMessageBuilder;
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
public class OrderProvider {

	/**
	 * Problem of sending different payloads to same topic
	 */

	private final StreamBridge streamBridge;

	@Scheduled(cron = "*/10 * * * * *")
	public void sendUnpaidOrder() {
		Message<UnpaidOrder> message = CloudEventMessageBuilder
				.withData(UnpaidOrder.builder()
									.id(UUID.randomUUID())
									.owner("gizem")
									.dueDate(LocalDate.now())
						            .outland(new Random().nextBoolean())
						            .build())
				.setHeader("partitionKey", "manual")
				.build();

		log.info("New unpaid order is sent on demand...");
		streamBridge.send("sendOrder-out-0", message);
	}

	@Scheduled(cron = "*/15 * * * * *")
	public void sendPrepaidOrder() {
		Message<PrepaidOrder> message = CloudEventMessageBuilder
				.withData(PrepaidOrder.builder()
									  .id(UUID.randomUUID())
									  .owner("gizem")
									  .paidAmount(BigDecimal.TEN)
									  .outland(new Random().nextBoolean())
									  .build())
				.setHeader("partitionKey", "manual")
				.build();

		log.info("New prepaid order is sent on demand...");
		streamBridge.send("sendOrder-out-0", message);
	}
}
