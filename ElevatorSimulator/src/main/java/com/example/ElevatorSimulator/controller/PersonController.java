package com.example.ElevatorSimulator.controller;

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
}
