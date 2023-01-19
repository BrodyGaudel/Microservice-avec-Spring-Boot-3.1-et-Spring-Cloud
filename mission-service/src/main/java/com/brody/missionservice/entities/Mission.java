package com.brody.missionservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MISSION")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mission {

    @Id
    @Column(name = "ID", nullable = false, unique = true )
    private String id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "DEPARTMENT_ID")
    private String departmentId;

    @Column(name = "EMPLOYEE_ID")
    private String employeeId;

}
