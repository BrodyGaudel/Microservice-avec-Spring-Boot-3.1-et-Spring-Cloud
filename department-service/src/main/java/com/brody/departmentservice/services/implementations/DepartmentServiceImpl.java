package com.brody.departmentservice.services.implementations;

import com.brody.departmentservice.dto.DepartmentDTO;
import com.brody.departmentservice.entities.Department;
import com.brody.departmentservice.exceptions.DepartmentNotFoundException;
import com.brody.departmentservice.mapping.Mappers;
import com.brody.departmentservice.repositories.DepartmentRepository;
import com.brody.departmentservice.services.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    private static final String DEPARTMENT_NOT_FOUND = "DEPARTMENT NOT FOUND";
    private static final String DEPARTMENT_FOUND = "DEPARTMENT(s) FOUND";
    private final DepartmentRepository departmentRepository;
    private final Mappers mappers;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, Mappers mappers) {
        this.departmentRepository = departmentRepository;
        this.mappers = mappers;
    }

    @Override
    public DepartmentDTO addNewDepartment(DepartmentDTO departmentDTO) {
        log.info("In addNewDepartment()");
        Department department = mappers.fromDepartmentDTO(departmentDTO);
        if(department == null){
            log.warn("Department Not Saved");
            return null;
        }else{
            department.setId(UUID.randomUUID().toString());
            Department departmentSaved = departmentRepository.save(department);
            log.info("Department Saved");
            return mappers.fromDepartment(departmentSaved);
        }
    }

    @Override
    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO) throws DepartmentNotFoundException {
        log.info("In updateDepartment()");
        Department department = departmentRepository.findById(departmentDTO.getId())
                .orElseThrow( () -> new DepartmentNotFoundException(DEPARTMENT_NOT_FOUND));

        department.setName(departmentDTO.getName());
        department.setDescription(departmentDTO.getDescription());

        Department departmentUpdated = departmentRepository.save(department);
        log.info("Department Updated");
        return mappers.fromDepartment(departmentUpdated);
    }

    @Override
    public DepartmentDTO getDepartmentById(String id) throws DepartmentNotFoundException {
        log.info("In getDepartmentById()");
        Department department = departmentRepository.findById(id)
                .orElseThrow( () -> new DepartmentNotFoundException(DEPARTMENT_NOT_FOUND));
        log.info(DEPARTMENT_FOUND);
        return mappers.fromDepartment(department);
    }

    @Override
    public List<DepartmentDTO> getDepartmentByName(String name) {
        log.info("In getDepartmentByName()");
        List<Department> departments = departmentRepository.findByNameContains(name);
        log.info(DEPARTMENT_FOUND);
        return mappers.fromDepartments(departments);
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        log.info("In getAllDepartments()");
        List<Department> departments = departmentRepository.findAll();
        log.info(DEPARTMENT_FOUND);
        return mappers.fromDepartments(departments);
    }

    @Override
    public void deleteDepartmentById(String id) {
        log.info("In deleteDepartmentById()");
        departmentRepository.deleteById(id);
        log.info("department deleted");

    }

    @Override
    public void deleteAllDepartments() {
        log.info("In deleteAllDepartments()");
        departmentRepository.deleteAll();
        log.info("all departments deleted");
    }
}
