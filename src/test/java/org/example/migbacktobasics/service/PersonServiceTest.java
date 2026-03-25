package org.example.migbacktobasics.service;

import org.example.migbacktobasics.dto.PersonRequest;
import org.example.migbacktobasics.entity.Person;
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
import static org.mockito.ArgumentMatchers.anyLong;
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
        person1.setId(1L);
        person1.setFirstName("Joe");
        person1.setFirstName("Johnson");

        var person2 = new Person();
        person2.setId(2L);
        person2.setFirstName("Jane");
        person2.setFirstName("Jackson");

        var persons = List.of(person1, person2);

        when(repository.findAll()).thenReturn(persons);

       var result = service.getAll();

        assertEquals(2, result.size());
        for (var person : result) {
            var expected = persons.stream().filter(e -> e.getId().equals(person.id())).findFirst().orElseThrow();
            assertEquals(expected.getFirstName(), person.firstName());
            assertEquals(expected.getLastName(), person.lastName());
        }
    }

    @Test
    void testCreate() {
        var person = new Person();
        person.setId(2L);

        when(repository.save(any(Person.class))).thenReturn(person);

        service.create(new PersonRequest("Jane", "Jackson"));

        verify(repository, times(1)).save(any(Person.class));
    }

    @Test
    void testCreate_failWithException() {
        var person = new Person();
        person.setId(1L);

        when(repository.save(any(Person.class))).thenReturn(person);

        var exception = assertThrows(RuntimeException.class, () -> service.create(new PersonRequest("Joe", "Johnson")));

        verify(repository, times(1)).save(any(Person.class));
        assertEquals("Something went wrong", exception.getMessage());
    }

    @Test
    void testDelete() throws InterruptedException {
        doNothing().when(repository).deleteById(anyLong());

        service.delete(1L);

        verify(repository, times(1)).deleteById(1L);
    }

}