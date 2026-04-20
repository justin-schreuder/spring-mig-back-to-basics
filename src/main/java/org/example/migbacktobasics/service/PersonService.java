package org.example.migbacktobasics.service;

import org.example.migbacktobasics.dto.PersonRequest;
import org.example.migbacktobasics.dto.PersonResponse;
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

    public List<PersonResponse> getAll() {
        return repository.findAll().stream()
            .map(this::toResponse)
            .toList();
    }

    @Transactional
    public void create(PersonRequest request) {
        Person model = new Person();
        model.setFirstName(request.firstName());
        model.setLastName(request.lastName());
        var saved = repository.save(model);
        if (saved.getId().contains("x")) {
            throw new RuntimeException("Something went wrong");
        }
    }

    @Async
    public void delete(String id) throws InterruptedException {
        Thread.sleep(10_000L);
        repository.deleteById(id);
    }

    private PersonResponse toResponse(Person person) {
        return new PersonResponse(person.getId(), person.getFirstName(), person.getLastName());
    }

}
