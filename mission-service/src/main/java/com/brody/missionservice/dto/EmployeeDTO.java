package com.brody.missionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDTO {
    private String id;
    private String firstname;
    private String name;
    private String nationality;
    private Date dateOfBirth;
    private String placeOfBirth;
    private String address;
    private String email;
    private String phone;
    private String cin;
    private Date lastUpdate;
    private ContractDTO contract;
    private String departmentId;
}
