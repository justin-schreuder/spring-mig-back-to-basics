package org.example.migbacktobasics.service;

import org.example.migbacktobasics.model.Person;
import org.example.migbacktobasics.repository.PersonRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void create(Person person) {
        var saved = repository.save(person);
        if (saved.getId().contains("x")) {
            throw new RuntimeException("Something went wrong");
        }
    }

    @Async
    public void delete(String id) throws InterruptedException {
        Thread.sleep(10_000L);
        repository.deleteById(id);
    }

}
