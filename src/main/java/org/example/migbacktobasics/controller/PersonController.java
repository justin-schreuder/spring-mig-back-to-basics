package org.example.migbacktobasics.controller;

import org.example.migbacktobasics.entity.Person;
import org.example.migbacktobasics.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<Person> getAll() {
        return service.getAll();
    }

    @PostMapping
    public void create(@RequestBody Person person) {
        service.create(person);
    }

}
