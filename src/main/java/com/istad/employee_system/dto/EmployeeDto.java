package com.istad.employee_system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

// پرانے کوڈ کے مطابق Employee کا نیا record
public record EmployeeDto(
        Long id,
        
        @NotBlank(message = "Full Name cannot be blank!")
        String fullName,
        
        @NotBlank(message = "Email cannot be blank!")
        @Email(message = "Invalid email format!")
        String email,
        
        String departmentName,
        
        String positionName
) {
}