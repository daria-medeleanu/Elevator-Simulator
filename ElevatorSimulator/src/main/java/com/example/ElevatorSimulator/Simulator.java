package com.example.ElevatorSimulator;

import com.example.ElevatorSimulator.entities.Elevator;
import com.example.ElevatorSimulator.entities.Person;
import com.example.ElevatorSimulator.repositories.PersonRepository;

import java.util.ArrayList;
import java.util.List;

public class Simulator {
    private static Simulator instance;
    private List<Elevator> elevators;
    public boolean running;
    public Simulator(){
        this.elevators = new ArrayList<>();
        this.elevators.add(new Elevator("Elevator 1"));
        this.elevators.add(new Elevator("Elevator 2"));
        this.running = true;
        initializeThreads();
    }
    public static synchronized Simulator getInstance(){
        if(instance == null){
            instance = new Simulator();
        }
        return instance;
    }
    public void initializeThreads() {
        for(Elevator elevator : elevators){
            Thread thread = new Thread(elevator);
            thread.start();
        }
    }
    public List<Elevator> getElevators(){
        return elevators;
    }

    public Elevator requestElevator(Person person, String direction) {
        Elevator fastestElevator = null;
        int shortestTime = Integer.MAX_VALUE;

        for(Elevator elevator : elevators){
//            int time = Math.abs(elevator.getCurrentFloor() - person.getCurrentFloor());
            int time = calculateCscanTime(elevator, person.getCurrentFloor(), direction.equals("up"));
            if (time < shortestTime) {
                shortestTime = time;
                fastestElevator = elevator;
            }
        }
        if(fastestElevator != null) {
            fastestElevator.addFloorRequest(person.getCurrentFloor());
        }
        System.out.println("asociem persoanei" + person.getName() + "liftul" + fastestElevator.getName()
        );
        person.setAssignedElevator(fastestElevator);
        System.out.println("assigned elevator " + person.getName() + person.getAssignedElevator().getName());
        return fastestElevator;
    }
    private int calculateCscanTime(Elevator elevator, int targetFloor, boolean goingUp) {
        int currentFloor = elevator.getCurrentFloor();
        if (goingUp) {
            return targetFloor >= currentFloor ? targetFloor - currentFloor : (9 - currentFloor) + targetFloor;
        } else {
            return targetFloor <= currentFloor ? currentFloor - targetFloor : currentFloor + (9 - targetFloor);
        }
    }
    public void setDestinationFloor(Elevator elevator, int destinationFloor){
        elevator.addFloorRequest(destinationFloor);
    }

    public Elevator getElevatorByName(String name) {
        return elevators.stream()
                .filter(elevator -> elevator.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

}
