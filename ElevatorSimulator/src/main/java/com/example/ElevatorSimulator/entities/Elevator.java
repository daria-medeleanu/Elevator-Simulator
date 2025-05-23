package com.example.ElevatorSimulator.entities;

import java.util.ArrayList;
import java.util.List;

public class Elevator implements Runnable{
    private String name;
    private int currentFloor;
    private boolean movingUp;
//    private boolean busy;
    private List<Integer> floorRequests;

    public Elevator(String name){
        this.name = name;
        this.currentFloor = 0;
        this.movingUp = true;
        this.floorRequests = new ArrayList<>();
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
           int nextFloor = floorRequests.get(0);
           if(currentFloor < nextFloor){
               movingUp = true;
               currentFloor++;
           } else if (currentFloor > nextFloor){
               movingUp = false;
               currentFloor--;
           } else {
               floorRequests.remove(0);
           }
       }

        System.out.println(getName() + " is at floor " + getCurrentFloor() + "and has this destinations" + getFloorRequests());

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
        //if(!floorRequests.contains(destinationFloor)){
            floorRequests.add(destinationFloor);
        //}
    }
    public List<Integer> getFloorRequests(){
        return this.floorRequests;
    }
}
