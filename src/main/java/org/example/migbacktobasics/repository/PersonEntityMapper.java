package org.example.migbacktobasics.repository;

import org.example.migbacktobasics.entity.PersonMongoEntity;
import org.example.migbacktobasics.entity.PersonSqlEntity;
import org.example.migbacktobasics.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedSourcePolicy = ReportingPolicy.WARN,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface PersonEntityMapper {

    Person entityToPerson(PersonMongoEntity entity);

    Person entityToPerson(PersonSqlEntity entity);

    PersonMongoEntity personToMongoEntity(Person person);

    PersonSqlEntity personToSqlEntity(Person person);

}
