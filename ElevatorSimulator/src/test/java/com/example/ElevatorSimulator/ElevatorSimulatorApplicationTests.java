//package com.example.ElevatorSimulator;
//
//import com.example.ElevatorSimulator.entities.Elevator;
//import com.example.ElevatorSimulator.entities.Person;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class ElevatorSimulatorApplicationTests {
//
//	@Test
//	void contextLoads() {
//	}
//	@Test
//	void testElevatorMovement() {
//		Simulator simulator = Simulator.getInstance();
//
//		// Get elevators
//		List<Elevator> elevators = simulator.getElevators();
//		Elevator elevator1 = elevators.get(0);
//		Elevator elevator2 = elevators.get(1);
//
//		// Create persons
//		Person person1 = new Person("Person 1", 0);
//		person1.setDestinationFloor(5);
//		person1.requestElevator();
//
//		Person person2 = new Person("Person 2", 3);
//		person2.setDestinationFloor(0);
//		person2.requestElevator();
//
//		// Run the simulation for some time
//		try {
//			Thread.sleep(10000); // wait for 10 seconds
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//
//		// Check if the elevators have moved to the correct floors
//		assertEquals(5, elevator1.getCurrentFloor());
//		assertEquals(0, elevator2.getCurrentFloor());
//	}
//
//}
