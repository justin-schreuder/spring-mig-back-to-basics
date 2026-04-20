package org.example.migbacktobasics.adapter;

import org.example.migbacktobasics.entity.PersonSqlEntity;
import org.example.migbacktobasics.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonSqlAdapter extends JpaRepository<PersonSqlEntity, String> {

    Optional<Person> findTopIdByOrderByIdDesc();

}
