package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.user.entity.Customer;
import com.udacity.jdnd.course3.critter.user.entity.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = convertDTOToEntity(customerDTO);
        return convertEntityToDTO(userService.saveCustomer(customer));
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customers = userService.getAllCustomers();
        return customers.stream().map(customer -> convertEntityToDTO(customer)).collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        throw new UnsupportedOperationException();
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = convertDTOToEntityEmployee(employeeDTO);
        return  convertEmployeeEntityToDTO(userService.saveEmployee(employee));
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return convertEmployeeEntityToDTO(userService.getEmployeeById(employeeId));
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        userService.updateEmployeeSchedule(daysAvailable,employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        List<Employee> employees = userService.getEmployeesForService(employeeDTO.getSkills(), employeeDTO.getDate());
        return employees.stream().map(employeeEntity -> convertEmployeeEntityToDTO(employeeEntity)).collect(Collectors.toList());
    }


    private Customer convertDTOToEntity(CustomerDTO customerDTO){
        Customer customerEntity = new Customer();
        BeanUtils.copyProperties(customerDTO,customerEntity);
        return customerEntity;
    }

    private CustomerDTO convertEntityToDTO(Customer customerEntity){
        CustomerDTO customerDto = new CustomerDTO();
        BeanUtils.copyProperties(customerEntity,customerDto);
        return customerDto;
    }

    private Employee convertDTOToEntityEmployee(EmployeeDTO employeeDTO){
        Employee employeeEntity = new Employee();
        BeanUtils.copyProperties(employeeDTO,employeeEntity);
        return employeeEntity;
    }

    private EmployeeDTO convertEmployeeEntityToDTO(Employee employeeEntity){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employeeEntity,employeeDTO);
        return employeeDTO;
    }
}
