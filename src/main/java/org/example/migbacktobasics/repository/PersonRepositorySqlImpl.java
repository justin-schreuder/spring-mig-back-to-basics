package org.example.migbacktobasics.repository;

import org.example.migbacktobasics.adapter.PersonSqlAdapter;
import org.example.migbacktobasics.entity.PersonSqlEntity;
import org.example.migbacktobasics.model.Person;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ConditionalOnProperty(prefix = "mig", name = "repository-type", havingValue = "sql", matchIfMissing = true)
public class PersonRepositorySqlImpl implements PersonRepository {

    private final PersonSqlAdapter adapter;

    public PersonRepositorySqlImpl(PersonSqlAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public List<Person> findAll() {
        return adapter.findAll()
            .stream()
            .map(this::toModel)
            .toList();
    }

    @Override
    public Person save(Person person) {
        var entity = toEntity(person);
        var saved = adapter.save(entity);
        return toModel(saved);
    }

    @Override
    public void deleteById(String id) {
        adapter.deleteById(id);
    }

    @Override
    public String getHighestId() {
        var person = adapter.findTopIdByOrderByIdDesc();
        if (person.isPresent()) {
            return person.get().getId();
        } else {
            return "-1";
        }
    }

    @Override
    public long getTotalNumber() {
        return adapter.count();
    }

    private PersonSqlEntity toEntity(Person person) {
        var entity = new PersonSqlEntity();
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        return entity;
    }

    private Person toModel(PersonSqlEntity entity) {
        var model = new Person();
        model.setId(entity.getId());
        model.setFirstName(entity.getFirstName());
        model.setLastName(entity.getLastName());
        return model;
    }

}
