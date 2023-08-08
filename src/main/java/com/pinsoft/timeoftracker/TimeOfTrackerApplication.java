package com.pinsoft.timeoftracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TimeOfTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimeOfTrackerApplication.class, args);
		System.out.println("qweqwe");
	}

}
