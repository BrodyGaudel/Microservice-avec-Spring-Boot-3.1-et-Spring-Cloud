package com.brody.missionservice.dto;

import com.brody.missionservice.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContractDTO {
    private String id;
    private String description;
    private Type type;
    private BigDecimal salary;
}
