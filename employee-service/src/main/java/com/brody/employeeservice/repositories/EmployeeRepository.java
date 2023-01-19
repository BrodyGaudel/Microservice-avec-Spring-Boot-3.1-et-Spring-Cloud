package com.brody.employeeservice.repositories;

import com.brody.employeeservice.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

    @Query("select e from Employee e where e.name like :kw")
    List<Employee> findByNameContains(@Param("kw") String keywords);

    @Query("select e from Employee e where e.firstname like :kw")
    List<Employee> findByFirstnameContains(@Param("kw") String keywords);

    @Query( value = "SELECT * FROM EMPLOYEE WHERE DEPARTMENT_ID = ?1", nativeQuery = true)
    List<Employee> findByDepartmentId(String id);
}
