package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.user.entity.Customer;
import com.udacity.jdnd.course3.critter.user.entity.Employee;
import com.udacity.jdnd.course3.critter.user.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    Employee saveEmployee(Employee employee)  {
        return employeeRepository.save(employee);
    }

    Employee getEmployeeById(Long employeeId){
        return employeeRepository.findById(employeeId).get();
    }
}
