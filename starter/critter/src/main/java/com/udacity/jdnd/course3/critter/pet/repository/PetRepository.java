package com.udacity.jdnd.course3.critter.pet.repository;

import com.udacity.jdnd.course3.critter.pet.entity.Pet;
import com.udacity.jdnd.course3.critter.user.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet , Long> {
    public List<Pet> getPetsByCustomer_Id(Long ownerId);

    @Query("SELECT p.customer FROM Pet p WHERE p.id = :petId")
    public Customer getCustomerByPetId(Long petId);
}
