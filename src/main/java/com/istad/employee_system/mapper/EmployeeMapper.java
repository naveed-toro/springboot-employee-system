package com.istad.employee_system.mapper;

import com.istad.employee_system.dto.EmployeeDto;
import com.istad.employee_system.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

// جدید طریقہ استعمال کرتے ہوئے وارننگز کو ختم کرنے کی پالیسی شامل کر دی گئی ہے
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {

    // Entity سے DTO (Record) میں تبدیل کرنے کا طریقہ
    // ہم MapStruct کو بتا رہے ہیں کہ Entity کے ناموں کو ملا کر fullName بنانا ہے
    @Mapping(target = "fullName", expression = "java(employee.getFirstName() + \" \" + employee.getLastName())")
    @Mapping(target = "departmentName", source = "department.departmentName")
    @Mapping(target = "positionName", source = "position.positionName")
    EmployeeDto toDto(Employee employee);

    // DTO سے Entity میں تبدیل کرنا
    // چونکہ Record میں فیلڈز کو تبدیل نہیں کیا جا سکتا، اس لیے ہم 'fullName' کو توڑ کر فرسٹ اور لاسٹ نیم نکالتے ہیں
    @Mapping(target = "firstName", expression = "java(dto.fullName().split(\" \")[0])")
    @Mapping(target = "lastName", expression = "java(dto.fullName().contains(\" \") ? dto.fullName().split(\" \")[1] : \"\")")
    @Mapping(target = "id", source = "id")
    Employee toEntity(EmployeeDto dto);
}