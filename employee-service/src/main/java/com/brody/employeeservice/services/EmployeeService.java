package com.brody.employeeservice.services;

import com.brody.employeeservice.dto.EmployeeDTO;
import com.brody.employeeservice.exceptions.*;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO addNewEmployee(EmployeeDTO employeeDTO) throws DepartmentNotFoundException, EmployeeCinException, EmployeePhoneException, EmployeeEmailException;
    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) throws EmployeeNotFoundException;
    EmployeeDTO getEmployeeById(String id) throws EmployeeNotFoundException;
    List<EmployeeDTO> getByFirstnameOrName(String keyword);
    List<EmployeeDTO> getAllEmployees();
    List<EmployeeDTO> getAllEmployeesByDepartmentId(String id);
    void deleteEmployeeById(String id);
    void deleteAllEmployees();
}
