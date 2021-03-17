package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.entity.Pet;
import com.udacity.jdnd.course3.critter.schedule.entity.Schedule;
import com.udacity.jdnd.course3.critter.user.entity.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleService.createSchedule(convertScheduleDTOToEntity(scheduleDTO));
        return convertScheduleEntityToDTO(schedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleService.getAllSchedules().stream()
                .map(schedule -> convertScheduleEntityToDTO(schedule))
                .collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return scheduleService.getScheduleForPet(petId).
                stream()
                .map(schedule -> convertScheduleEntityToDTO(schedule))
                .collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return scheduleService.getScheduleForEmployee(employeeId)
                .stream()
                .map(schedule -> convertScheduleEntityToDTO(schedule))
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return scheduleService.getScheduleForCustomer(customerId)
                .stream()
                .map(schedule -> convertScheduleEntityToDTO(schedule))
                .collect(Collectors.toList());
    }

    private Schedule convertScheduleDTOToEntity(ScheduleDTO dto){
        Schedule scheduleEntity = new Schedule();
        BeanUtils.copyProperties(dto,scheduleEntity);
        scheduleEntity.setEmployees(createEmployeeListFromIds(dto.getEmployeeIds()));
        scheduleEntity.setPets(createPetListFromIds(dto.getPetIds()));
        return scheduleEntity;
    }

    private ScheduleDTO convertScheduleEntityToDTO(Schedule scheduleEntity){
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(scheduleEntity,scheduleDTO);
        scheduleDTO.setEmployeeIds(scheduleEntity.getEmployees().stream().map(employee -> employee.getId()).collect(Collectors.toList()));
        scheduleDTO.setPetIds(scheduleEntity.getPets().stream().map(pet -> pet.getId()).collect(Collectors.toList()));
        return scheduleDTO;
    }

    private List<Employee> createEmployeeListFromIds(List<Long> employeeIds){
        return employeeIds.stream().map(employeeID -> {
            Employee employee = new Employee();
            employee.setId(employeeID);
            return employee;
        }).collect(Collectors.toList());
    }

    private List<Pet> createPetListFromIds(List<Long> petIds){
        return petIds.stream().map(petId -> {
            Pet pet = new Pet();
            pet.setId(petId);
            return pet;
        }).collect(Collectors.toList());
    }

}
