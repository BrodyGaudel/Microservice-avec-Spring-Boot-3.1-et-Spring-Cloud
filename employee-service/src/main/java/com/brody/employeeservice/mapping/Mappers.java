package com.brody.employeeservice.mapping;

import com.brody.employeeservice.dto.EmployeeDTO;
import com.brody.employeeservice.entities.Employee;

import java.util.List;

public interface Mappers {
    Employee fromEmployeeDTO(EmployeeDTO employeeDTO);
    EmployeeDTO fromEmployee(Employee employee);
    List<EmployeeDTO> fomEmployees(List<Employee> employees);
}
