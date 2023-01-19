package com.brody.employeeservice.mapping.implementation;

import com.brody.employeeservice.dto.ContractDTO;
import com.brody.employeeservice.dto.EmployeeDTO;
import com.brody.employeeservice.entities.Contract;
import com.brody.employeeservice.entities.Employee;
import com.brody.employeeservice.mapping.Mappers;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MappersImpl implements Mappers {

    @Override
    public Employee fromEmployeeDTO(EmployeeDTO employeeDTO) {
        try{

            Employee employee = new Employee();
            employee.setDepartmentId(employeeDTO.getDepartmentId());
            employee.setId(employeeDTO.getId());
            employee.setCin(employeeDTO.getCin());
            employee.setAddress(employeeDTO.getAddress());
            employee.setEmail(employeeDTO.getEmail());
            employee.setDateOfBirth(employeeDTO.getDateOfBirth());
            employee.setPlaceOfBirth(employeeDTO.getPlaceOfBirth());
            employee.setNationality(employeeDTO.getNationality());
            employee.setFirstname(employeeDTO.getFirstname());
            employee.setName(employeeDTO.getName());
            employee.setPhone(employeeDTO.getPhone());
            employee.setLastUpdate(employeeDTO.getLastUpdate());

            Contract contract = new Contract();
            contract.setId(employeeDTO.getContract().getId());
            contract.setType(employeeDTO.getContract().getType());
            contract.setDescription(employeeDTO.getContract().getDescription());
            contract.setSalary(employeeDTO.getContract().getSalary());
            contract.setEmployee(employee);

            employee.setContract(contract);

            return employee;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public EmployeeDTO fromEmployee(Employee employee) {
        try{
            EmployeeDTO employeeDTO = new EmployeeDTO();

            employeeDTO.setPlaceOfBirth(employee.getPlaceOfBirth());
            employeeDTO.setNationality(employee.getNationality());
            employeeDTO.setFirstname(employee.getFirstname());
            employeeDTO.setName(employee.getName());
            employeeDTO.setId(employee.getId());
            employeeDTO.setCin(employee.getCin());
            employeeDTO.setAddress(employee.getAddress());
            employeeDTO.setEmail(employee.getEmail());
            employeeDTO.setDateOfBirth(employee.getDateOfBirth());
            employeeDTO.setPhone(employee.getPhone());
            employeeDTO.setLastUpdate(employee.getLastUpdate());

            ContractDTO contractDTO = new ContractDTO(
                    employee.getContract().getId(),
                    employee.getContract().getDescription(),
                    employee.getContract().getType(),
                    employee.getContract().getSalary()
            );

            employeeDTO.setContract(contractDTO);
            employeeDTO.setDepartmentId(employee.getDepartmentId());

            return employeeDTO;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<EmployeeDTO> fomEmployees(List<Employee> employees) {
        try {
            return employees.stream().map(this::fromEmployee).toList();
        }catch (Exception e){
            return Collections.emptyList();
        }
    }
}
