package org.example.migbacktobasics.repository;

import org.example.migbacktobasics.adapter.PersonMongoAdapter;
import org.example.migbacktobasics.model.Person;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ConditionalOnProperty(prefix = "mig", name = "repository-type", havingValue = "mongo")
public class PersonRepositoryMongoImpl implements PersonRepository {

    private final PersonMongoAdapter adapter;
    private final PersonEntityMapper mapper;

    public PersonRepositoryMongoImpl(PersonMongoAdapter adapter, PersonEntityMapper mapper) {
        this.adapter = adapter;
        this.mapper = mapper;
    }

    @Override
    public List<Person> findAll() {
        return adapter.findAll()
            .stream()
            .map(mapper::entityToPerson)
            .toList();
    }

    @Override
    public Person save(Person person) {
        var entity = mapper.personToMongoEntity(person);
        var saved = adapter.save(entity);
        return mapper.entityToPerson(saved);
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

}
