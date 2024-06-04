package com.example.ElevatorSimulator;

import com.example.ElevatorSimulator.entities.Elevator;

public class DestinationRequest {
    private Long personId;
    private int destinationFloor;
    private String direction;

    public DestinationRequest(Long personId, int destinationFloor, String direction) {
        this.personId = personId;
        this.destinationFloor = destinationFloor;
        this.direction = direction;
    }

    public Long getPersonId() { return personId; }
    public void setPersonId(Long personId) { this.personId = personId; }
    public int getDestinationFloor() { return destinationFloor; }
    public void setDestinationFloor(int destinationFloor) { this.destinationFloor = destinationFloor; }
    public String getDirection() { return direction; }
    public void setDirection(String direction) { this.direction = direction; }
}
