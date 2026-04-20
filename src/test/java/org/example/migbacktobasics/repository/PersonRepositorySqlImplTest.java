package org.example.migbacktobasics.repository;

import org.example.migbacktobasics.adapter.PersonSqlAdapter;
import org.example.migbacktobasics.entity.PersonSqlEntity;
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
class PersonRepositorySqlImplTest {

    @Autowired
    private PersonSqlAdapter adapter;

    private PersonSqlEntity person1;

    @BeforeEach
    void setUp() {
        person1 = new PersonSqlEntity();
        person1.setFirstName("Joe");
        person1.setLastName("Johnson");
        adapter.save(person1);
    }

    @AfterEach
    void tearDown() {
        adapter.delete(person1);
    }

    @Test
    void testFindAll() {
        var persons = adapter.findAll();

        assertEquals(1, persons.size());
        var person = persons.get(0);
        assertEquals("Joe", person.getFirstName());
        assertEquals("Johnson", person.getLastName());
    }

    @Test
    void testSave() {
        var person2 = new PersonSqlEntity();
        person2.setFirstName("Jane");
        person2.setLastName("Jackson");
        adapter.save(person2);

        assertEquals(2, adapter.count());
    }

    @Test
    void testDelete() {
        adapter.delete(person1);

        assertEquals(0, adapter.count());
    }

}
