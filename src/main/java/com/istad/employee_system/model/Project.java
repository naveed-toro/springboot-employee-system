package com.istad.employee_system.model;

import jakarta.persistence.*;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // پروجیکٹ کا نام (جیسے Website Development, Mobile App)
    @Column(name = "project_name")
    private String projectName;

    // خالی کنسٹرکٹر
    public Project() {
    }

    // پیرامیٹر والا کنسٹرکٹر
    public Project(String projectName) {
        this.projectName = projectName;
    }

    // --- Getters and Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}