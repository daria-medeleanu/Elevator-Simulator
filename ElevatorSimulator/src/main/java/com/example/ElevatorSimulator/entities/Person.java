package com.example.ElevatorSimulator.entities;

import com.example.ElevatorSimulator.Simulator;
import jakarta.persistence.*;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "currentfloor")
    private int currentFloor;
    private int destinationFloor;

    public Person(){

    }
    public Person(String name, int currentFloor){
        this.name = name;
        this.currentFloor = currentFloor;
        this.destinationFloor = destinationFloor;
    }
    public Long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getCurrentFloor(){
        return currentFloor;
    }
    public void setCurrentFloor(int currentFloor){
        this.currentFloor = currentFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }
    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    public void requestElevator(){
        System.out.println(name + " is requesting an elevator to floor " + currentFloor);
        Simulator.getInstance().requestElevator(this);
    }

}
