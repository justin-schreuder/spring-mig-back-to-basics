package org.example.migbacktobasics.repository;

import org.example.migbacktobasics.model.Person;

import java.util.List;

public interface PersonRepository {

    List<Person> findAll();

    Person save(Person person);

    void deleteById(String id);

    String getHighestId();

    long getTotalNumber();

}
