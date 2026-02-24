package org.example.migbacktobasics.service;

import org.example.migbacktobasics.dto.PersonRequest;
import org.example.migbacktobasics.dto.PersonResponse;
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

    public List<PersonResponse> getAll() {
        return repository.findAll().stream()
            .map(this::toResponse)
            .toList();
    }

    public void create(PersonRequest person) {
        Person entity = new Person();
        entity.setFirstName(person.firstName());
        entity.setLastName(person.lastName());
        repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private PersonResponse toResponse(Person person) {
        return new PersonResponse(person.getId(), person.getFirstName(), person.getLastName());
    }

}
