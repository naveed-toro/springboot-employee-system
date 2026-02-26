package com.istad.employee_system.dto;

public class EmployeeDto {

    private Long id;
    
    // ہم نے firstName اور lastName کو ملا کر یہ ایک فیلڈ بنا دی ہے
    private String fullName;
    
    private String email;
    
    // ہم ڈیپارٹمنٹ کی پوری دیگچی نہیں بھیجیں گے، صرف اس کا نام بھیجیں گے
    private String departmentName;
    
    // ہم پوزیشن (عہدہ) کا بھی صرف نام بھیجیں گے
    private String positionName;

    // خالی کنسٹرکٹر
    public EmployeeDto() {
    }

    // پیرامیٹر والا کنسٹرکٹر
    public EmployeeDto(Long id, String fullName, String email, String departmentName, String positionName) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.departmentName = departmentName;
        this.positionName = positionName;
    }

    // --- Getters and Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
}