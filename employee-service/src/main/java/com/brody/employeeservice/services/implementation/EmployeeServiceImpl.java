package com.brody.employeeservice.services.implementation;

import com.brody.employeeservice.dto.DepartmentDTO;
import com.brody.employeeservice.dto.EmployeeDTO;
import com.brody.employeeservice.entities.Employee;
import com.brody.employeeservice.exceptions.*;
import com.brody.employeeservice.feign.DepartmentRestClient;
import com.brody.employeeservice.mapping.Mappers;
import com.brody.employeeservice.repositories.EmployeeRepository;
import com.brody.employeeservice.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private static final String EMPLOYEE_NOT_FOUND = "EMPLOYEE NOT FOUND";
    private static final String EMPLOYEE_FOUND = "EMPLOYEE(S) FOUND";

    private final EmployeeRepository employeeRepository;
    private final DepartmentRestClient departmentRestClient;
    private final Mappers mappers;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRestClient departmentRestClient, Mappers mappers) {
        this.employeeRepository = employeeRepository;
        this.departmentRestClient = departmentRestClient;
        this.mappers = mappers;
    }

    @Override
    public EmployeeDTO addNewEmployee(EmployeeDTO employeeDTO) throws DepartmentNotFoundException, EmployeeCinException,
            EmployeePhoneException, EmployeeEmailException {
        log.info("In addNewEmployee()");
        DepartmentDTO department = departmentRestClient.getDepartmentById(employeeDTO.getDepartmentId());

        Employee employee = mappers.fromEmployeeDTO(employeeDTO);

        boolean cin = false;
        boolean phone = false;
        boolean email = false;

        List<Employee> employees = employeeRepository.findAll();
        for(Employee e: employees){
            if(e.getCin().equals(employee.getCin())){
                cin = true;
            }
            if(e.getEmail().equals(employee.getEmail())){
                email = true;
            }
            if(e.getPhone().equals(employee.getPhone())){
                phone = true;
            }
        }

        if(cin){
            throw new EmployeeCinException("THE ARE ALREADY AN EMPLOYEE WITH THIS CIN");
        }
        if (phone){
            throw new EmployeePhoneException("THE ARE ALREADY AN EMPLOYEE WITH THIS PHONE NUMBER");
        }
        if(email) {
            throw new EmployeeEmailException("THE ARE ALREADY AN EMPLOYEE WITH THIS EMAIL");
        }

        employee.setDepartmentId(department.getId());
        employee.setLastUpdate(null);
        String id = UUID.randomUUID().toString();
        employee.setId(id);
        employee.getContract().setId(id);

        Employee employeeSaved = employeeRepository.save(employee);
        log.info("EMPLOYEE SAVED");
        return mappers.fromEmployee(employeeSaved);
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) throws EmployeeNotFoundException {
        log.info("In updateEmployee()");
        Employee employee = employeeRepository.findById(employeeDTO.getId())
                .orElseThrow( () -> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND));

        Employee employee1 = mappers.fromEmployeeDTO(employeeDTO);
        if(employee1 == null){
            return null;
        }else {
            employee1.setDepartmentId(employee.getDepartmentId());
            employee1.setId(employee.getId());
            employee1.getContract().setId(employee.getContract().getId());
            employee1.setLastUpdate(new Date());

            Employee employee2 = employeeRepository.save(employee1);
            log.info("EMPLOYEE UPDATED");
            return mappers.fromEmployee(employee2);
        }
    }

    @Override
    public EmployeeDTO getEmployeeById(String id) throws EmployeeNotFoundException {
        log.info("In getEmployeeById()");
        Employee employee = employeeRepository.findById(id)
                .orElseThrow( () -> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND));
        log.info(EMPLOYEE_FOUND);
        return mappers.fromEmployee(employee);
    }

    @Override
    public List<EmployeeDTO> getByFirstnameOrName(String keyword) {
        log.info("In getByFirstnameOrName()");
        List<Employee> employeesByFirstname = employeeRepository.findByFirstnameContains(keyword);
        List<Employee> employeesByName = employeeRepository.findByNameContains(keyword);
        List<Employee> employeeList = new ArrayList<>();
        employeeList.addAll(employeesByFirstname);
        employeeList.addAll(employeesByName);
        log.info(EMPLOYEE_FOUND);
        return mappers.fomEmployees(employeeList);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        log.info("In getAllEmployees()");
        List<Employee> employees = employeeRepository.findAll();
        log.info(EMPLOYEE_FOUND);
        return mappers.fomEmployees(employees);
    }

    @Override
    public List<EmployeeDTO> getAllEmployeesByDepartmentId(String id) {
        log.info("In getAllEmployeesByDepartmentId()");
        List<Employee> employees = employeeRepository.findByDepartmentId(id);
        log.info(EMPLOYEE_FOUND);
        return mappers.fomEmployees(employees);
    }

    @Override
    public void deleteEmployeeById(String id) {
        log.info("In deleteEmployeeById()");
        employeeRepository.deleteById(id);
        log.info("EMPLOYEE DELETED");

    }

    @Override
    public void deleteAllEmployees() {
        log.info("In deleteAll()");
        employeeRepository.deleteAll();
        log.info("ALL EMPLOYEES DELETED");
    }
}
