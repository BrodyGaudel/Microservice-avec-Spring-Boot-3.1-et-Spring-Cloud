package com.brody.missionservice.repositories;

import com.brody.missionservice.entities.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, String> {
    @Query("select m from Mission m where m.title like :kw")
    List<Mission> findByTitleContains(@Param("kw") String keywords);

    @Query( value = "SELECT * FROM MISSION WHERE DEPARTMENT_ID = ?1", nativeQuery = true)
    List<Mission> finByDepartmentId(String id);

    @Query( value = "SELECT * FROM MISSION WHERE EMPLOYEE_ID = ?1", nativeQuery = true)
    List<Mission> finByEmployeeId(String id);
}
