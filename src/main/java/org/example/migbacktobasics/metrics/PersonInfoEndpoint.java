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
        var maxId = personRepository.getHighestId();
        Map<String, String> personDetails = Map.of(
            "total", String.valueOf(personRepository.getTotalNumber()),
            "maxId", maxId
        );
        builder.withDetail("persons", personDetails);
    }

}
