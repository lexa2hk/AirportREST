package com.example.AirportREST;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.Serializable;

@SpringBootApplication
@EnableScheduling
public class AirportRestApplication implements Serializable {

	public static void main(String[] args) {
		SpringApplication.run(AirportRestApplication.class, args);
	}
}
