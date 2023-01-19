package com.brody.departmentservice.services;

import com.brody.departmentservice.dto.DepartmentDTO;
import com.brody.departmentservice.exceptions.DepartmentNotFoundException;

import java.util.List;

public interface DepartmentService {
    DepartmentDTO addNewDepartment(DepartmentDTO departmentDTO);
    DepartmentDTO updateDepartment(DepartmentDTO departmentDTO) throws DepartmentNotFoundException;
    DepartmentDTO getDepartmentById(String id) throws DepartmentNotFoundException;
    List<DepartmentDTO> getDepartmentByName(String name);
    List<DepartmentDTO> getAllDepartments();
    void deleteDepartmentById(String id);
    void deleteAllDepartments();
}
