package com.brody.missionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MissionDTO {
    private String id;
    private String title;
    private String description;
    private String departmentId;
    private String employeeId;

}
