package com.udacity.jdnd.course3.critter.pet.entity;

import com.udacity.jdnd.course3.critter.pet.PetType;
import com.udacity.jdnd.course3.critter.user.entity.Customer;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Pet {

    @Id
    @GeneratedValue
    private Long id;

    private PetType type;
    private String name;
    private long ownerId;
    private LocalDate birthDate;
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
