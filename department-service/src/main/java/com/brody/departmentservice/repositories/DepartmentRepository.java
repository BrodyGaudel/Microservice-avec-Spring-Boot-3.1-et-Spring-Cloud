package com.brody.departmentservice.repositories;

import com.brody.departmentservice.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, String> {
    @Query("select d from Department d where d.name like :kw")
    List<Department> findByNameContains(@Param("kw") String keywords);
}
