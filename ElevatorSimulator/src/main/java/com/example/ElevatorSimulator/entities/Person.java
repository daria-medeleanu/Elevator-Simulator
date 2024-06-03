package com.example.ElevatorSimulator.entities;

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

    public Person(){

    }
    public Person(String name, int currentFloor){
        this.name = name;
        this.currentFloor = currentFloor;
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


}
