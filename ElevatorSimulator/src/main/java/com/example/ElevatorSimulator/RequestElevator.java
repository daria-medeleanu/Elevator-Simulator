package com.example.ElevatorSimulator;

import com.example.ElevatorSimulator.entities.Person;

public class RequestElevator {
    private Person person;
    private String direction;
    public Person getPerson(){
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
