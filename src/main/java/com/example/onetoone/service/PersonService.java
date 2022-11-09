package com.example.onetoone.service;

import com.example.onetoone.entity.AddressEntity;
import com.example.onetoone.entity.PersonEntity;
import com.example.onetoone.model.Address;
import com.example.onetoone.model.Person;
import com.example.onetoone.model.PersonResponse;
import com.example.onetoone.repository.AddressRepository;
import com.example.onetoone.repository.PersonRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class PersonService {
    private final AddressRepository addressRepository;
    private final PersonRepository personRepository;

    public PersonService(AddressRepository addressRepository, PersonRepository personRepository) {
        this.addressRepository = addressRepository;
        this.personRepository = personRepository;
    }


    public PersonResponse createPerson(Person person) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddress1(person.getAddress().getAddress1());
        addressEntity.setAddress2(person.getAddress().getAddress2());
        addressEntity.setCity(person.getAddress().getCity());
        addressEntity.setState(person.getAddress().getState());
        addressEntity.setZipCode(person.getAddress().getZipCode());
        addressRepository.save(addressEntity);

        PersonEntity personEntity = new PersonEntity();
        personEntity.setFirstName(person.getFirstName());
        personEntity.setLastName(person.getLastName());
        personEntity.setAddress(addressEntity);
        personRepository.save(personEntity);

        PersonResponse personResponse = new PersonResponse();
        personResponse.setPersonId(personEntity.getPersonId());
        log.info("created with id {}", personResponse);
        return personResponse;


    }

    public Person getById(Long personId) {
        Address address = new Address();
        Person person = new Person();
        Optional<PersonEntity> personEntity = personRepository.findById(personId);
        Optional<AddressEntity> addressEntity = addressRepository.findById(personEntity.get().getAddress().getAddressId());
        if (personEntity.isPresent() && addressEntity.isPresent()) {
            log.info("person " + personEntity.get().getPersonId() + " is found");
            address.setAddress1(addressEntity.get().getAddress1());
            address.setAddress2(addressEntity.get().getAddress2());
            address.setCity(addressEntity.get().getCity());
            address.setState(addressEntity.get().getState());
            address.setZipCode(addressEntity.get().getZipCode());

            person.setFirstName(personEntity.get().getFirstName());
            person.setLastName(personEntity.get().getLastName());
            person.setAddress(address);
        } else {
            log.info("person " + personEntity.get().getPersonId() + " is not found ");
        }
        return person;

    }

    public void deleteById(Long personId) {
        Optional<PersonEntity> personEntity = personRepository.findById(personId);
        if (personEntity.isPresent()) {
            personRepository.deleteById(personId);
            addressRepository.deleteById(personEntity.get().getAddress().getAddressId());
            log.info("person deleted with id {}", personId);
        } else {
            log.info("person with id {} not found", personId);
        }
    }

    public Person updatePerson(Long personId, Person person) {

        PersonEntity personEntity = personRepository.findById(personId).get();
        AddressEntity addressEntity = addressRepository.findById(personEntity.getAddress().getAddressId()).get();

        addressEntity.setAddress1(person.getAddress().getAddress1());
        addressEntity.setAddress2(person.getAddress().getAddress2());
        addressEntity.setCity(person.getAddress().getCity());
        addressEntity.setState(person.getAddress().getState());
        addressRepository.save(addressEntity);

        personEntity.setFirstName(person.getFirstName());
        personEntity.setLastName(person.getLastName());
        personEntity.setAddress(addressEntity);
        personRepository.save(personEntity);

        return person;

    }

}
