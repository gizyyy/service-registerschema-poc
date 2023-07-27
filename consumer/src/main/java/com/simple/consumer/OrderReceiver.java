package com.simple.consumer;

import com.simple.events.avro.PrepaidOrder;
import java.util.Optional;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.function.context.MessageRoutingCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

@Configuration
@Slf4j
public class OrderReceiver {

  private static final String LOG_TEMPLATE = "{}\n---\nHEADERS: {}\n...\nPAYLOAD: {}\n---";

  @Bean
  public MessageRoutingCallback messageRoutingCallback() {
    return new MessageRoutingCallback() {
      @Override
      public FunctionRoutingResult routingResult(Message<?> message) {
        String functionDefinition = Optional.of(message.getHeaders())
            .map(messageHeaders -> messageHeaders.get("ce_type").toString())
            .map(type -> EventTypeToBinding.findByKey(type)).get();
        return new FunctionRoutingResult(functionDefinition);
      }
    };
  }

  @Bean
  public Consumer<Message<?>> unknownEvent() {
    return message -> log.warn("Received unknown event!");
  }

  @Bean
  public Consumer<Message<PrepaidOrder>> prepaidOrder() {
    return message -> log.info(LOG_TEMPLATE, "Prepaidorder!", message.getHeaders(),
        message.getPayload().getPaidAmount());
  }


}