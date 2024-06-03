package com.example.ElevatorSimulator.controller;

import com.example.ElevatorSimulator.DestinationRequest;
import com.example.ElevatorSimulator.RequestElevator;
import com.example.ElevatorSimulator.Simulator;
import com.example.ElevatorSimulator.entities.Elevator;
import com.example.ElevatorSimulator.entities.Person;
import com.example.ElevatorSimulator.repositories.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
@CrossOrigin(origins = "http://localhost:3000")
public class PersonController {
    private final PersonRepository personRepository;
    public PersonController(PersonRepository personRepository){
        this.personRepository = personRepository;
    }
    @GetMapping
    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }
    @PostMapping
    public void createPerson(@RequestBody Person person){
        personRepository.save(person);
    }
    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        personRepository.deleteById(id);
    }
    @PostMapping("/request-elevator")
    public Elevator requestElevator(@RequestBody RequestElevator request){
        Person person = request.getPerson();
        String direction = request.getDirection();

        Elevator assignedElevator =  Simulator.getInstance().requestElevator(person, direction);

        person.setAssignedElevator(assignedElevator);
        assignedElevator.addPerson(person); // Add the person to the elevator's list of persons
        personRepository.save(person);

        return assignedElevator;
    }

    @PostMapping("/set-destination")
    public void setDestination(@RequestBody DestinationRequest destinationRequest) {
        System.out.println("Acesta este id-ul: " + destinationRequest.getPersonId() + " iar aceasta este destinatia: " + destinationRequest.getDestinationFloor());
        Optional<Person> optionalPerson = personRepository.findById(destinationRequest.getPersonId());
        if (!optionalPerson.isPresent()) {
            System.out.println("No person found with ID " + destinationRequest.getPersonId());
            return;
        }
        Person person = optionalPerson.get();
        Elevator assignedElevator = person.getAssignedElevator();
        if (assignedElevator == null) {
            System.out.println("No elevator assigned to person with ID " + destinationRequest.getPersonId());
            return;
        }
        Simulator.getInstance().setDestinationFloor(assignedElevator, destinationRequest.getDestinationFloor());
    }

    @GetMapping("/elevator/{elevatorName}/persons")
    public List<Person> getPersonsInElevator(@PathVariable String elevatorName){
        Elevator elevator = Simulator.getInstance().getElevatorByName(elevatorName);
        if (elevator == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Elevator not found");
        }
        return elevator.getPersons();
    }
}
