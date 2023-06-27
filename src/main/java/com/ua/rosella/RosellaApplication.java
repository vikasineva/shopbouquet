package com.ua.rosella;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.ua.rosella.repository")
public class RosellaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RosellaApplication.class, args);
	}

}
