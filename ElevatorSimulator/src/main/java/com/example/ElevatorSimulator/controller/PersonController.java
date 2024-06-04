package com.example.ElevatorSimulator.controller;

import com.example.ElevatorSimulator.DestinationRequest;
import com.example.ElevatorSimulator.RequestElevator;
import com.example.ElevatorSimulator.Simulator;
import com.example.ElevatorSimulator.entities.Elevator;
import com.example.ElevatorSimulator.entities.Person;
import com.example.ElevatorSimulator.repositories.PersonRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/persons")
@CrossOrigin(origins = "http://localhost:3000")
public class PersonController {
    private final PersonRepository personRepository;
    public PersonController(PersonRepository personRepository){
        this.personRepository = personRepository;
        Simulator.getInstance();
    }
    @GetMapping
    public List<Person> getAllPersons(){
        return personRepository.getAllAuthors();
    }
    @PostMapping
    public void createPerson(@RequestBody Person person){
        personRepository.addPerson(person);
    }
    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        personRepository.deleteById(id);
    }
    @PostMapping("/request-elevator")
    public void requestElevator(@RequestBody RequestElevator request){
        Person person = request.getPerson();
        String direction = request.getDirection();
        person.setAssignedElevator(Simulator.getInstance().requestElevator(person, direction));
        System.out.println("requestElevator person-controller" +person.getAssignedElevator());
    }

    @PostMapping("/set-destination")
    public void setDestination(@RequestBody DestinationRequest destinationRequest) {
        System.out.println("Acesta este id-ul: " + destinationRequest.getPersonId() + " iar aceasta este destinatia: " + destinationRequest.getDestinationFloor());
        Person person = personRepository.findById(destinationRequest.getPersonId());
        System.out.println("Aceasta este persoana" + person.getName() + person.getCurrentFloor() + person.getAssignedElevator());
//        Elevator assignedElevator = person.getAssignedElevator();
//        System.out.println("Liftul " + assignedElevator.getName() + "e ok");
//        Simulator.getInstance().setDestinationFloor(assignedElevator, destinationRequest.getDestinationFloor());
    }
}
