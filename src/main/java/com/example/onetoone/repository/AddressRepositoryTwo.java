package com.example.onetoone.repository;

import com.example.onetoone.entity.AddressEntityTwo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepositoryTwo extends JpaRepository<AddressEntityTwo,Long> {
}
