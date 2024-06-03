package com.example.ElevatorSimulator.entities;

public class Elevator implements Runnable{
    private String name;
    private int currentFloor;
    private boolean movingUp;

    public Elevator(String name){
        this.name = name;
        this.currentFloor = 0;
        this.movingUp = true;
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
        if (movingUp) {
            if (currentFloor < 9) {
                currentFloor++;
            } else {
                movingUp = false;
            }
        } else {
            if (currentFloor > 0) {
                currentFloor--;
            } else {
                movingUp = true;
            }
        }
        System.out.println(getName() + " is at floor " + getCurrentFloor());
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
}
