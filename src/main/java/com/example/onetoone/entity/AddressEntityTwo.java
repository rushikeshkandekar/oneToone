package com.example.onetoone.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Table(name = "address_two")
public class AddressEntityTwo {
    @Id
    private Long id;
    @Column(name = "address_one")
    private String address1;
    @Column(name = "address_two")
    private String address2;
    private String city;
    private String state;
    @Column(name = "zipcode")
    private String zipCode;
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "id")

    private PersonEntityTwo person;
}
