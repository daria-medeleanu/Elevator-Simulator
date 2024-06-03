package com.example.ElevatorSimulator.repositories;

import com.example.ElevatorSimulator.entities.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Transactional
public class PersonRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Person> getAllAuthors() {
        return entityManager.createQuery("SELECT p FROM Person p",Person.class).getResultList();
    }
    public void addPerson(Person person){
        entityManager.persist(person);
    }
    public void deleteById(Long id){
        Person person = entityManager.find(Person.class,id);
        if(person!=null){
            entityManager.remove(person);
        }
    }
    public Person findById(Long id){
        return entityManager.find(Person.class, id);
    }
}
