package com.example.aws.springbootpostgresaws.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "employee_image")
public class EmployeeImage {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "s3_key", nullable = false, length=200)
    private String key;

    @Column(name = "url", nullable = false, length=1000)
    private String url;

    public EmployeeImage(String key, String url) {
        this.key = key;
        this.url = url;
    }
}
