package com.example.onetoone.mapper;

import com.example.onetoone.entity.PersonEntityTwo;
import com.example.onetoone.model.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonEntityTwo personToEntity(Person person);
    Person entityToPerson(PersonEntityTwo personEntityTwo);
}
