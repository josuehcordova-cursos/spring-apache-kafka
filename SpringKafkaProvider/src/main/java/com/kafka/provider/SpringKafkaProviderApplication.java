package com.kafka.provider;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import com.kafka.provider.model.User;

@SpringBootApplication
public class SpringKafkaProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaProviderApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate, KafkaTemplate<String, User> userKafkaTemplate){
		return args -> {
			//1. Send string to Kafka
			kafkaTemplate.send("unProgramadorNace-topic", "La anyiyi!");

			//2. Send User objects to Kafka
			User user = new User();
			user.setUserId(1);
			user.setFirstName("firstname");
			user.setLastName("lastname");

			userKafkaTemplate.send("user-log",user);
		};
	}
}
