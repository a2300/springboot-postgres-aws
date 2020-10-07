package com.example.aws.springbootpostgresaws.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee   {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @OneToOne(cascade = {CascadeType.ALL})
    private EmployeeImage employeeImage;

    public Employee(String name, EmployeeImage employeeImage) {
        this.name = name;
        this.employeeImage = employeeImage;
    }
}
