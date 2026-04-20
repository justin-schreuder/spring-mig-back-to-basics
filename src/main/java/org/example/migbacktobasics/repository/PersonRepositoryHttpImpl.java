package org.example.migbacktobasics.repository;

import net.datafaker.Faker;
import org.example.migbacktobasics.model.Person;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@ConditionalOnProperty(prefix = "mig", name = "repository-type", havingValue = "http")
public class PersonRepositoryHttpImpl implements PersonRepository {

    private final Faker faker;
    private final SecureRandom random;

    public PersonRepositoryHttpImpl() {
        this.faker = new Faker();
        this.random = new SecureRandom();
    }

    @Override
    public List<Person> findAll() {
        var persons = new ArrayList<Person>();
        for (int i = 0; i < random.nextInt(1, 11); i++) {
            var e = new Person();
            e.setId(UUID.randomUUID().toString());
            e.setFirstName(faker.name().firstName());
            e.setLastName(faker.name().lastName());
            persons.add(e);
        }
        return persons;
    }

    @Override
    public Person save(Person person) {
        var temp = new Person();
        temp.setId(UUID.randomUUID().toString());
        temp.setId(person.getFirstName());
        temp.setId(person.getLastName());
        return temp;
    }

    @Override
    public void deleteById(String id) {
        // do nothing
    }

    @Override
    public String getHighestId() {
        return String.valueOf(random.nextLong(0, 1001));
    }

    @Override
    public long getTotalNumber() {
        return random.nextLong(0, 1001);
    }

}
