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
//    @PostMapping("/request-elevator")
//    public Elevator requestElevator(@RequestBody RequestElevator request){
//        Person person = request.getPerson();
//        String direction = request.getDirection();
//
//        Elevator assignedElevator = Simulator.getInstance().requestElevator(person, direction);
//        System.out.println("Acesta este liftul asignat" + person.getName() + " "+ person.getAssignedElevator().getName() + "lift" );
//        return assignedElevator;
//    }
//
//    @PostMapping("/set-destination")
//    public void setDestination(@RequestBody DestinationRequest destinationRequest) {
//        System.out.println("Acesta este id-ul: " + destinationRequest.getPersonId() + " iar aceasta este destinatia: " + destinationRequest.getDestinationFloor());
//        Person person = personRepository.findById(destinationRequest.getPersonId());
////        System.out.println("Aceasta este persoana" + person.getDestinationFloor());
//        Elevator assignedElevator = person.getAssignedElevator();
////        System.out.println("Liftul " + assignedElevator.getName() + "e ok");
////        Simulator.getInstance().setDestinationFloor(assignedElevator, destinationRequest.getDestinationFloor());
//        if (assignedElevator != null) {
//            System.out.println("Liftul " + assignedElevator.getName() + " e ok");
//            Simulator.getInstance().setDestinationFloor(assignedElevator, destinationRequest.getDestinationFloor());
//        } else {
//            System.out.println("Error: Person " + person.getName() + " does not have an assigned elevator.");
//        }
//    }
    @PostMapping("/request-elevator")
    public void requestElevatorAndSetDestination(@RequestBody DestinationRequest destinationRequest){
        Person person = personRepository.findById(destinationRequest.getPersonId());
        String direction = destinationRequest.getDirection();
        Elevator assignedElevator = Simulator.getInstance().requestElevator(person, direction);
        person.setAssignedElevator(assignedElevator);
        System.out.println("Acesta este liftul asignat:" + person.getName() + " " + person.getAssignedElevator().getName() + "lift");
        System.out.println("Acesta este id-ul: " + destinationRequest.getPersonId() + " iar aceasta este destinatia: " + destinationRequest.getDestinationFloor());
        if (assignedElevator != null) {
            System.out.println("Liftul " + assignedElevator.getName() + " e ok");
            Simulator.getInstance().setDestinationFloor(assignedElevator, destinationRequest.getDestinationFloor());
        } else {
            System.out.println("Error: Person " + person.getName() + " does not have an assigned elevator.");
        }
    }
}
