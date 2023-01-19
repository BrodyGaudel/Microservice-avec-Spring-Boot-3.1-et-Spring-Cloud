package com.brody.employeeservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "EMPLOYEE")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Employee {
    @Id
    @Column(name = "ID", nullable = false, unique = true )
    private String id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstname;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "NATIONALITY", nullable = false)
    private String nationality;

    @Column(name = "DATE_OF_BIRTH", nullable = false)
    private Date dateOfBirth;

    @Column(name = "PLACE_OF_BIRTH", nullable = false)
    private String placeOfBirth;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "EMAIL", nullable = false, unique = true )
    private String email;

    @Column(name = "PHONE", nullable = false, unique = true )
    private String phone;

    @Column(name = "CIN", nullable = false, unique = true )
    private String cin;

    @Column(name = "LAST_UPDATE")
    private Date lastUpdate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CONTRACT_ID", referencedColumnName = "ID")
    private Contract contract;

    @Column(name = "DEPARTMENT_ID")
    private String departmentId;

}
