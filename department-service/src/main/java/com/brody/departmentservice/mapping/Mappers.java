package com.brody.departmentservice.mapping;

import com.brody.departmentservice.dto.DepartmentDTO;
import com.brody.departmentservice.entities.Department;

import java.util.List;

public interface Mappers {

    /**
     * Map DepartmentDTO to Department
     * @param departmentDTO DepartmentDTO
     * @return Department
     */
    Department fromDepartmentDTO(DepartmentDTO departmentDTO);

    /**
     * Map Department to DepartmentDTO
     * @param department Department
     * @return DepartmentDTO
     */
    DepartmentDTO fromDepartment(Department department);

    /**
     * Map List<Department> to List<DepartmentDTO>
     * @param departments List<Department>
     * @return List<DepartmentDTO>
     */
    List<DepartmentDTO> fromDepartments(List<Department> departments);
}
