package com.istad.employee_system.mapper;

import com.istad.employee_system.dto.DepartmentDto;
import com.istad.employee_system.model.Department;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

// unmappedTargetPolicy = ReportingPolicy.IGNORE لگانے سے وارننگز ختم ہو جائیں گی
// componentModel کے لیے اب ہم MappingConstants کا جدید طریقہ استعمال کر رہے ہیں
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartmentMapper {
    
    // Entity سے DTO (Record) میں تبدیل کرنا
    DepartmentDto toDto(Department department);
    
    // DTO سے Entity میں تبدیل کرنا
    Department toEntity(DepartmentDto departmentDto);
}