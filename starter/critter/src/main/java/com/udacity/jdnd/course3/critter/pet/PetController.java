package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.pet.entity.Pet;
import com.udacity.jdnd.course3.critter.user.entity.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    PetService petService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet savedPet = petService.savePet(convertPetDTOToEntity(petDTO));
        return convertPetEntityToDTO(savedPet);
    }


    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return convertPetEntityToDTO(petService.findPetByID(petId));
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> pets = petService.findPets();
        return pets.stream().map(pet -> convertPetEntityToDTO(pet)).collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return petService.getPetsByOwnerId(ownerId).stream().map(pet -> convertPetEntityToDTO(pet)).collect(Collectors.toList());
    }

    private PetDTO convertPetEntityToDTO(Pet petEntity){
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(petEntity,petDTO);
        petDTO.setOwnerId(petEntity.getCustomer().getId());
        return petDTO;
    }

    private Pet convertPetDTOToEntity(PetDTO petDTO){
        Pet petEntity = new Pet();
        BeanUtils.copyProperties(petDTO,petEntity);
        return updatePetEntityWithCustomerId(petDTO,petEntity);
    }

    private Pet updatePetEntityWithCustomerId(PetDTO petDTO, Pet pet) {
        Customer customer = pet.getCustomer();
        customer.setId(petDTO.getOwnerId());
        pet.setCustomer(customer);
        return pet;
    }
}
