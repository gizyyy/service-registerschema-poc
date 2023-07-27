package com.simple.producer;

import com.simple.events.avro.PrepaidOrder;
import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.function.cloudevent.CloudEventMessageBuilder;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderProvider {

  /**
   * Problem of sending different payloads to same topic
   */

  private final StreamBridge streamBridge;


  @Scheduled(cron = "*/15 * * * * *")
  public void sendPrepaidOrder() {
    Message<PrepaidOrder> message = CloudEventMessageBuilder
        .withData(PrepaidOrder.newBuilder()
            .setId(UUID.randomUUID().toString())
            .setOwner("gizem")
            .setPaidAmount(BigDecimal.TEN)
            .setOutland(new Random().nextBoolean())
            .build())
        .setHeader("partitionKey", "manual")
        .build();

    log.info("New prepaid order is sent on demand...");
    streamBridge.send("sendOrder-out-0", message);
  }
}
