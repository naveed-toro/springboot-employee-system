package com.istad.employee_system.model;

import jakarta.persistence.*;

@Entity
@Table(name = "positions")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // عہدے کا نام (جیسے Manager, Developer)
    @Column(name = "position_name")
    private String positionName;

    // خالی کنسٹرکٹر
    public Position() {
    }

    // پیرامیٹر والا کنسٹرکٹر
    public Position(String positionName) {
        this.positionName = positionName;
    }

    // --- Getters and Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
}