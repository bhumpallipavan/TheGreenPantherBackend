package com.example.TheGreenPanther;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class TheGreenPantherApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheGreenPantherApplication.class, args);
	}

}
