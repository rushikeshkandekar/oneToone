package com.example.onetoone.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Table(name = "person_two")
public class PersonEntityTwo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    private  String firstName;
    private  String lastName;
    @PrimaryKeyJoinColumn(name = "id")
    @OneToOne(mappedBy ="person",cascade = CascadeType.ALL)
    private AddressEntityTwo address;

}
