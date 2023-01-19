package com.brody.departmentservice.services.implementations;

import com.brody.departmentservice.dto.DepartmentDTO;
import com.brody.departmentservice.entities.Department;
import com.brody.departmentservice.exceptions.DepartmentNotFoundException;
import com.brody.departmentservice.repositories.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceImplTest {

    @Autowired
    private DepartmentServiceImpl service;
    @Autowired
    private DepartmentRepository repository;

    @Test
    void addNewDepartment() {
        DepartmentDTO departmentDTO = service.addNewDepartment(new DepartmentDTO(
                null,
                "Software Engineering",
                "This department is an IT"
        ));
        assertNotNull(departmentDTO);
    }

    @Test
    void updateDepartment() throws DepartmentNotFoundException {
        Department department = repository.save(new Department(
                UUID.randomUUID().toString(),
                "Hardware Engineering",
                "This department is responsible for defining the architecture"
        ));

        DepartmentDTO departmentDTO = service.updateDepartment(new DepartmentDTO(
                department.getId(),
                "Cloud Computing Engineering",
                "This department is responsible for cloud solution"
        ));

        assertNotNull(departmentDTO);
        assertEquals(department.getId(),departmentDTO.getId());


    }

    @Test
    void getDepartmentById() throws DepartmentNotFoundException {
        Department department = repository.save(new Department(
                UUID.randomUUID().toString(),
                "Data Engineering",
                "This department is responsible for enterprise business intelligence"
        ));

        DepartmentDTO departmentDTO = service.getDepartmentById(department.getId());
        assertNotNull(departmentDTO);
        assertEquals(department.getId(), departmentDTO.getId());
    }

    @Test
    void getDepartmentByName() {
        Department department = repository.save(new Department(
                UUID.randomUUID().toString(),
                "Network Infrastructure and Data Security",
                "This department is responsible for network and data security"
        ));
        List<DepartmentDTO> departmentDTOS = service.getDepartmentByName(department.getName());
        assertNotNull(departmentDTOS);
    }

    @Test
    void getAllDepartments() {
        repository.save(new Department(
                UUID.randomUUID().toString(),
                "Wireless Intelligence Network",
                "This department is responsible for new network intelligence generation"
        ));
        List<DepartmentDTO> departmentDTOS = service.getAllDepartments();
        assertNotNull(departmentDTOS);
    }

    @Test
    void deleteDepartmentById() {
        Department department = repository.save(new Department(
                UUID.randomUUID().toString(),
                "ioT System and Service",
                "This department is responsible for ioT implementations"
        ));
        service.deleteDepartmentById(department.getId());
        Department department1 = repository.findById(department.getId()).orElse(null);
        assertNull(department1);
    }

    @Test
    void deleteAllDepartments() {
        repository.save(new Department(
                UUID.randomUUID().toString(),
                "System and Software Embedded",
                "This department is responsible for system embedded implementations"
        ));
        service.deleteAllDepartments();
        List<Department> departments = repository.findAll();
        if(departments.isEmpty()){
            departments = null;
        }
        assertNull(departments);
    }
}