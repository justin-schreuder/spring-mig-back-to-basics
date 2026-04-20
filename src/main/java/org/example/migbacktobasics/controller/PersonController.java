package org.example.migbacktobasics.controller;

import jakarta.validation.Valid;
import org.example.migbacktobasics.dto.PersonRequest;
import org.example.migbacktobasics.dto.PersonResponse;
import org.example.migbacktobasics.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping("/public")
    public String publicApi() {
        return "I am open to all!";
    }

    @PreAuthorize("hasRole('VIEW')")
    @GetMapping("/all")
    public List<PersonResponse> getAll() {
        return service.getAll();
    }

    @PreAuthorize("hasRole('EDIT')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody PersonRequest person) {
        service.create(person);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) throws InterruptedException {
        service.delete(id);
    }

}
