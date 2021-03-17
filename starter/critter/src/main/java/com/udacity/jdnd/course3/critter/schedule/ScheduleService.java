package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.pet.entity.Pet;
import com.udacity.jdnd.course3.critter.schedule.entity.Schedule;
import com.udacity.jdnd.course3.critter.schedule.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.user.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    PetService petService;

    public Schedule createSchedule(Schedule schedule){
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules(){
        return scheduleRepository.findAll();
    }

    public List<Schedule> getScheduleForPet(Long id){
        Pet pet = new Pet();
        pet.setId(id);
        return scheduleRepository.getScheduleByPets(pet);
    }

    public List<Schedule> getScheduleForEmployee(Long id){
        Employee employee = new Employee();
        employee.setId(id);
        return scheduleRepository.getScheduleByEmployees(employee);
    }

    public List<Schedule> getScheduleForCustomer(Long customerId){
        List<Pet> pets = petService.getPetsByOwnerId(customerId);
        return scheduleRepository.getScheduleByPetsIn(pets);
    }
}
