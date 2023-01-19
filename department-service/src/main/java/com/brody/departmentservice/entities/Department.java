package com.brody.departmentservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "DEPARTMENT")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Department {

    @Id
    @Column(name = "ID", nullable = false, unique = true )
    private String id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;
}
