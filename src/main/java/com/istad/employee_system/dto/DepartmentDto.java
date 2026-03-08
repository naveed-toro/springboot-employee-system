package com.istad.employee_system.dto;

import jakarta.validation.constraints.NotBlank;

// پرانے کوڈ کے مطابق Department کا نیا record
public record DepartmentDto(
        Long id,
        
        @NotBlank(message = "Department Name cannot be blank!")
        String departmentName,
        
        String departmentDescription
) {
}