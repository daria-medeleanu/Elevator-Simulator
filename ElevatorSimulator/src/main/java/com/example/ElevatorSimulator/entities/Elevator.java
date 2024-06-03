package com.example.ElevatorSimulator.entities;

import java.util.ArrayList;
import java.util.List;

public class Elevator implements Runnable{
    private String name;
    private int currentFloor;
    private boolean movingUp;
    private List<Integer> floorRequests;
    private List<Person> persons;

    public Elevator(String name){
        this.name = name;
        this.currentFloor = 0;
        this.movingUp = true;
        this.floorRequests = new ArrayList<>();
        this.persons = new ArrayList<>();
    }
    @Override
    public void run(){
        while(!Thread.currentThread().isInterrupted()){
            move();
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
    public void move() {
       if(!floorRequests.isEmpty()){
           int nearestFloor = findNearestFloor();
           if(currentFloor < nearestFloor){
               movingUp = true;
               currentFloor++;
           } else if (currentFloor > nearestFloor){
               movingUp = false;
               currentFloor--;
           } else {
               floorRequests.remove(Integer.valueOf(nearestFloor));
           }
       }

        System.out.println(getName() + " is at floor " + getCurrentFloor());
    }

    private int findNearestFloor() {
        return floorRequests.stream()
                .min((floor1, floor2) -> Math.abs(currentFloor - floor1) - Math.abs(currentFloor - floor2))
                .orElse(currentFloor);
    }
    public int getCurrentFloor(){
        return currentFloor;
    }
    public String getName() {
        return name;
    }
    public boolean getMovingUp(){
        return movingUp;
    }

    public void addFloorRequest(int destinationFloor) {
        if(!floorRequests.contains(destinationFloor)){
            floorRequests.add(destinationFloor);
        }
    }

    public List<Person> getPersons(){
        return persons;
    }

    public void addPerson(Person person){
        persons.add(person);
    }

    public void removePerson(Person person){
        persons.remove(person);
    }
}
