package com.example.onetoone.controller;

import com.example.onetoone.model.Person;
import com.example.onetoone.model.PersonResponse;
import com.example.onetoone.service.PersonServiceTwo;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonControllerTwo {

    public final PersonServiceTwo personServiceTwo;

    public PersonControllerTwo(PersonServiceTwo personServiceTwo) {
        this.personServiceTwo = personServiceTwo;
    }

    @PostMapping(value = "/address", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonResponse> createPersontwo(@RequestBody Person person) {
        PersonResponse personResponse = personServiceTwo.createPersonTwo(person);
        return new ResponseEntity<>(personResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/address/{personId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> getById(@PathVariable Long personId) {
        Person person = personServiceTwo.getById(personId);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @DeleteMapping(value = "/address/{personId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteById(@PathVariable Long personId) {
        personServiceTwo.deleteById(personId);
        return ResponseEntity.ok().build();

    }

    @PutMapping(value = "/address/{personId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> updatePerson(@PathVariable Long personId, @RequestBody Person person) {
        person = personServiceTwo.updatePerson(personId, person);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

}
