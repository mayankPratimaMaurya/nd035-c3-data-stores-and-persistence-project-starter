package com.udacity.jdnd.course3.critter.pet.entity;

import com.udacity.jdnd.course3.critter.user.entity.Customer;

import javax.persistence.*;

@Entity
public class Pet {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    Customer customer;

}
