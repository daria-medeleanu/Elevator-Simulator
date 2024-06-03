package com.example.ElevatorSimulator.controller;

import com.example.ElevatorSimulator.Simulator;
import com.example.ElevatorSimulator.entities.Elevator;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/elevators")
@CrossOrigin(origins = "http://localhost:3000")
public class ElevatorController {
    private List<Elevator> elevators;

    public ElevatorController(){
        elevators = Stream.of(
                new Elevator("Elevator 1"),
                new Elevator("Elevator 2")
        ).collect(Collectors.toList());
    }
    @GetMapping
    public List<Elevator> getElevators(){
        return Simulator.getInstance().getElevators();
    }
}
