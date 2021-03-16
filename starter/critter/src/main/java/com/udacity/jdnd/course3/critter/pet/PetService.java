package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.pet.entity.Pet;
import com.udacity.jdnd.course3.critter.pet.repository.PetRepository;
import com.udacity.jdnd.course3.critter.user.UserService;
import com.udacity.jdnd.course3.critter.user.entity.Customer;
import com.udacity.jdnd.course3.critter.user.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PetService {
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private UserService userService;

    public Pet savePet(Pet pet){
        Pet savedPet = petRepository.save(pet);
        if(savedPet.getCustomer() != null)
            userService.addPetToCustomer(savedPet);

        return savedPet;
    }

    public Pet findPetByID(Long petId){
        return petRepository.findById(petId).get();
    }

    public List<Pet> findPets(){
        return petRepository.findAll();
    }

    public List<Pet> getPetsByOwnerId(Long ownerID){
        return petRepository.getPetsByCustomer_Id(ownerID);
    }

    public Customer getOwnerByPetID(Long petId){
        return petRepository.getCustomerByPetId(petId);
    }
}
