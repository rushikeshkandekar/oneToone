package com.example.onetoone.repository;

import com.example.onetoone.entity.PersonEntityTwo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepositoryTwo extends JpaRepository<PersonEntityTwo,Long> {
}
