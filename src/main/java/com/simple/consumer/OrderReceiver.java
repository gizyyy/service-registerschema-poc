package com.simple.consumer;

import java.util.function.Consumer;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
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

	@Bean
	public KStream<String, Message> processHighSchoolStudents(StreamsBuilder kStreamBuilder) {

		KStream<String, Message> inputStream = kStreamBuilder.stream("order-topic",
				Consumed.with(Serdes.String(), Serdes.serdeFrom(Message.class)));

		KStream<String, Message> inlandStream = inputStream
				.filter((key, value) -> ((Order) value.getPayload()).isOutland()).peek((key, value) -> System.out
						.println("key:" + key + " value:" + ((Order) value.getPayload()).getOwner()));

		inlandStream.to("receiveOrder-out-0");
		return inlandStream;
	}

}
