package com.example.ElevatorSimulator;

import com.example.ElevatorSimulator.entities.Elevator;
import com.example.ElevatorSimulator.entities.Person;

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

    public void requestElevator(Person person) {
        Elevator fastestElevator = null;
        int shortestTime = Integer.MAX_VALUE;

        for(Elevator elevator : elevators){
            int time = Math.abs(elevator.getCurrentFloor() - person.getCurrentFloor());
            if(time < shortestTime){
                shortestTime = time;
                fastestElevator = elevator;
            }
        }

        if(fastestElevator != null) {
            fastestElevator.addFloorRequest(person.getDestinationFloor());
        }
    }
}
