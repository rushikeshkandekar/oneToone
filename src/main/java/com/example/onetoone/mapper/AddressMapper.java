package com.example.onetoone.mapper;

import com.example.onetoone.entity.AddressEntityTwo;
import com.example.onetoone.model.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressEntityTwo addressToEntity(Address address);
    Address entityToAdress(AddressEntityTwo addressEntityTwo);
}
