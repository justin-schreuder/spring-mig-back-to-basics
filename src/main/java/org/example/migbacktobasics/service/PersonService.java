package org.example.migbacktobasics.service;

import org.example.migbacktobasics.entity.Person;
import org.example.migbacktobasics.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public List<Person> getAll() {
        return repository.findAll();
    }

    public void create(Person person) {
        repository.save(person);
    }

}
