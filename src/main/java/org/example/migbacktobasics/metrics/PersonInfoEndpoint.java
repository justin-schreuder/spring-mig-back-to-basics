package org.example.migbacktobasics.metrics;

import org.example.migbacktobasics.repository.PersonRepository;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PersonInfoEndpoint implements InfoContributor {

    private final PersonRepository personRepository;

    public PersonInfoEndpoint(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void contribute(Info.Builder builder) {
        var maxPerson = personRepository.findTopIdByOrderByIdDesc();
        var maxId = maxPerson.isPresent() ? maxPerson.get().getId() : -1L;
        Map<String, Long> personDetails = Map.of(
            "total", personRepository.count(),
            "maxId", maxId
        );
        builder.withDetail("persons", personDetails);
    }

}
