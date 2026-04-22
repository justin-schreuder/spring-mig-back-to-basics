package org.example.migbacktobasics.service;

import org.example.migbacktobasics.model.Person;
import org.example.migbacktobasics.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private PersonService service;

    @Test
    void testGetAll() {
        var person1 = new Person();
        person1.setId("xyz987");
        person1.setFirstName("Joe");
        person1.setFirstName("Johnson");

        var person2 = new Person();
        person2.setId("abc123");
        person2.setFirstName("Jane");
        person2.setFirstName("Jackson");

        var persons = List.of(person1, person2);

        when(repository.findAll()).thenReturn(persons);

       var result = service.getAll();

        assertEquals(2, result.size());
        for (var person : result) {
            var expected = persons.stream().filter(e -> e.getId().equals(person.getId())).findFirst().orElseThrow();
            assertEquals(expected.getFirstName(), person.getFirstName());
            assertEquals(expected.getLastName(), person.getLastName());
        }
    }

    @Test
    void testCreate() {
        var mockPerson = new Person();
        mockPerson.setId("abc123");

        when(repository.save(any(Person.class))).thenReturn(mockPerson);

        var person = new Person();
        person.setFirstName("Jane");
        person.setLastName("Jackson");

        service.create(person);

        verify(repository, times(1)).save(any(Person.class));
    }

    @Test
    void testCreate_failWithException() {
        var mockPerson = new Person();
        mockPerson.setId("xyz987");

        when(repository.save(any(Person.class))).thenReturn(mockPerson);

        var person = new Person();
        person.setFirstName("Joe");
        person.setLastName("Johnson");

        var exception = assertThrows(RuntimeException.class, () -> service.create(person));

        verify(repository, times(1)).save(any(Person.class));
        assertEquals("Something went wrong", exception.getMessage());
    }

    @Test
    void testDelete() throws InterruptedException {
        doNothing().when(repository).deleteById(anyString());

        service.delete("xyz987");

        verify(repository, times(1)).deleteById("xyz987");
    }

}