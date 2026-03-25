package org.example.migbacktobasics.repository;

import org.example.migbacktobasics.entity.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest(properties = {
    "spring.datasource.url=jdbc:h2:mem:testdb",
    "spring.jpa.hibernate.ddl-auto=create-drop"
})
class PersonRepositoryTest {

    @Autowired
    private PersonRepository repository;

    private Person person1;

    @BeforeEach
    void setUp() {
        person1 = new Person();
        person1.setFirstName("Joe");
        person1.setLastName("Johnson");
        repository.save(person1);
    }

    @AfterEach
    void tearDown() {
        repository.delete(person1);
    }

    @Test
    void testFindAll() {
        var persons = repository.findAll();

        assertEquals(1, persons.size());
        var person = persons.get(0);
        assertEquals("Joe", person.getFirstName());
        assertEquals("Johnson", person.getLastName());
    }

    @Test
    void testSave() {
        var person2 = new Person();
        person2.setFirstName("Jane");
        person2.setLastName("Jackson");
        repository.save(person2);

        assertEquals(2, repository.count());
    }

    @Test
    void testDelete() {
        repository.delete(person1);

        assertEquals(0, repository.count());
    }

}
