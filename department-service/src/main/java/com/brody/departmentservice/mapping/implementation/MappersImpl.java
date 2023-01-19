package com.brody.departmentservice.mapping.implementation;

import com.brody.departmentservice.dto.DepartmentDTO;
import com.brody.departmentservice.entities.Department;
import com.brody.departmentservice.mapping.Mappers;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MappersImpl implements Mappers {
    @Override
    public Department fromDepartmentDTO(DepartmentDTO departmentDTO) {
        try{
            return new Department(
                    departmentDTO.getId(),
                    departmentDTO.getName(),
                    departmentDTO.getDescription()
            );
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public DepartmentDTO fromDepartment(Department department) {
        try{
            return new DepartmentDTO(
                    department.getId(),
                    department.getName(),
                    department.getDescription()
            );
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<DepartmentDTO> fromDepartments(List<Department> departments) {
        try{
            return departments.stream().map(this::fromDepartment).toList();
        }catch (Exception e){
            return Collections.emptyList();
        }
    }
}
