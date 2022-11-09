package com.example.onetoone.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    private Long addressId;
    @Column(name = "address_one")
    private String address1;
    @Column(name = "address_two")
    private String address2;
    private String city;
    private String state;
    @Column(name = "zipcode")
    private String zipCode;

    @OneToOne(mappedBy = "address")
    private PersonEntity person;

}
