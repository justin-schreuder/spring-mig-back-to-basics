package org.example.migbacktobasics.repository;

import org.example.migbacktobasics.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findTopIdByOrderByIdDesc();

}
