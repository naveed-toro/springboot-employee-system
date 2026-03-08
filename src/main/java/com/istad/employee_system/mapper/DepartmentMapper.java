package com.istad.employee_system.mapper;

import com.istad.employee_system.dto.DepartmentDto;
import com.istad.employee_system.model.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    
    // Entity سے DTO میں تبدیل کرنا
    DepartmentDto toDto(Department department);
    
    // DTO سے Entity میں تبدیل کرنا
    Department toEntity(DepartmentDto departmentDto);
}