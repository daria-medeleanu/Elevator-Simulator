package com.example.ElevatorSimulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElevatorSimulatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElevatorSimulatorApplication.class, args);
		Simulator sim = new Simulator();
	}

}
