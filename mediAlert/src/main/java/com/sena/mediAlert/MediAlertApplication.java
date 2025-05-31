package com.sena.mediAlert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MediAlertApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediAlertApplication.class, args);
	}

}
