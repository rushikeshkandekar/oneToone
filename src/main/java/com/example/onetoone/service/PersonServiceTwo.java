package com.example.onetoone.service;

import com.example.onetoone.entity.AddressEntityTwo;
import com.example.onetoone.entity.PersonEntityTwo;
import com.example.onetoone.mapper.PersonMapper;
import com.example.onetoone.model.Person;
import com.example.onetoone.model.PersonResponse;
import com.example.onetoone.repository.AddressRepositoryTwo;
import com.example.onetoone.repository.PersonRepositoryTwo;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class PersonServiceTwo {
    private final AddressRepositoryTwo addressRepositoryTwo;

    private final PersonMapper personMapper;
    private final PersonRepositoryTwo personRepositoryTwo;

    public PersonServiceTwo(AddressRepositoryTwo addressRepositoryTwo, PersonMapper personMapper, PersonRepositoryTwo personRepositoryTwo) {
        this.addressRepositoryTwo = addressRepositoryTwo;

        this.personMapper = personMapper;
        this.personRepositoryTwo = personRepositoryTwo;
    }

    public PersonResponse createPersonTwo(Person person) {
        PersonEntityTwo personEntityTwo;
        personEntityTwo = personMapper.personToEntity(person);
        personEntityTwo.getAddress().setPerson(personEntityTwo);
        personRepositoryTwo.save(personEntityTwo);
        PersonResponse personResponse = new PersonResponse();
        personResponse.setPersonId(personEntityTwo.getId());
        log.info("created with id {}", personResponse);
        return personResponse;


    }

    @Cacheable(cacheNames = "address")

    public Person getById(Long personId) {
        Optional<PersonEntityTwo> personEntityTwo = personRepositoryTwo.findById(personId);
        Person person = new Person();
        if (personEntityTwo.isPresent()) {
            person = personMapper.entityToPerson(personEntityTwo.get());
            log.info("person" + personId + "successfull get");
        } else {
            log.info("person" + personId + "not found");
        }

        return person;
    }

    @CacheEvict(cacheNames = "address")
    public void deleteById(Long personId) {
        Optional<PersonEntityTwo> personEntityTwo = personRepositoryTwo.findById(personId);
        if (personEntityTwo.isPresent()) {
            personRepositoryTwo.deleteById(personId);
            log.info("deleted successfully");
        } else {
            log.info("person" + personId + "not found");
        }

    }

    @CachePut(cacheNames = "address", key = "#personId")

    public Person updatePerson(Long personId, Person person) {

        PersonEntityTwo personEntityTwo = personRepositoryTwo.findById(personId).get();
        AddressEntityTwo addressEntityTwo = addressRepositoryTwo.findById(personEntityTwo.getAddress().getId()).get();


        addressEntityTwo.setAddress1(person.getAddress().getAddress1());
        addressEntityTwo.setAddress2(person.getAddress().getAddress2());
        addressEntityTwo.setCity(person.getAddress().getCity());
        addressEntityTwo.setState(person.getAddress().getState());

        addressRepositoryTwo.save(addressEntityTwo);

        personEntityTwo.setFirstName(person.getFirstName());
        personEntityTwo.setLastName(person.getLastName());

        personEntityTwo.setAddress(addressEntityTwo);

        personRepositoryTwo.save(personEntityTwo);


        return person;
    }
}