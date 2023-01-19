package com.brody.employeeservice.dto;

import com.brody.employeeservice.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ContractDTO {
    private String id;
    private String description;
    private Type type;
    private BigDecimal salary;
}
