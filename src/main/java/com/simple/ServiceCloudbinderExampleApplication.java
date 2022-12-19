package com.simple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableKafka
@EnableKafkaStreams
public class ServiceCloudbinderExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceCloudbinderExampleApplication.class, args);
	}

}
