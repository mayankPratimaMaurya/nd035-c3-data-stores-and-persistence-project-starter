package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.pet.entity.Pet;
import com.udacity.jdnd.course3.critter.pet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PetService {
    @Autowired
    private PetRepository petRepository;

    public Pet savePet(Pet pet){

        return petRepository.save(pet);
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
}
