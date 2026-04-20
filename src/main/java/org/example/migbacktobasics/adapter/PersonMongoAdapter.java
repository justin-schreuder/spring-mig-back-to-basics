package org.example.migbacktobasics.adapter;

import org.example.migbacktobasics.entity.PersonMongoEntity;
import org.example.migbacktobasics.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PersonMongoAdapter extends MongoRepository<PersonMongoEntity, String> {

    Optional<Person> findTopIdByOrderByIdDesc();

}
