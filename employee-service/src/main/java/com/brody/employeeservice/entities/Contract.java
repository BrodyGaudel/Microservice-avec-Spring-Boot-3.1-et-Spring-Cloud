package com.brody.employeeservice.entities;

import com.brody.employeeservice.enums.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "CONTRACT")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Contract {

    @Id
    @Column(name = "ID", nullable = false, unique = true )
    private String id;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "SALARY", nullable = false)
    private BigDecimal salary;

    @Transient
    @OneToOne(mappedBy = "contract")
    private Employee employee;

    public Contract(String id, String description, Type type, BigDecimal salary) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.salary = salary;
    }
}
