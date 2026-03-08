package com.istad.employee_system.service;

import com.istad.employee_system.dto.DepartmentDto;
import com.istad.employee_system.exception.ResourceNotFoundException;
import com.istad.employee_system.mapper.DepartmentMapper;
import com.istad.employee_system.model.Department;
import com.istad.employee_system.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ڈیپارٹمنٹ سروس کی امپلیمنٹیشن کلاس۔
 * اب ہم دوبارہ Lombok استعمال کر رہے ہیں کیونکہ فائل کا نام ٹھیک ہو چکا ہے۔
 */
@Service
@RequiredArgsConstructor // لومبوک خود بخود فائنل فیلڈز کے لیے کنسٹرکٹر بنا دے گا
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> 
            new ResourceNotFoundException("معذرت! اس آئی ڈی کے ساتھ کوئی ڈیپارٹمنٹ موجود نہیں ہے: " + id));
    }

    @Override
    public Department updateDepartment(Long id, Department departmentDetails) {
        Department existingDepartment = departmentRepository.findById(id).orElseThrow(() -> 
            new ResourceNotFoundException("معذرت! اپ ڈیٹ کے لیے اس آئی ڈی کا ڈیپارٹمنٹ نہیں ملا: " + id));
        
        existingDepartment.setDepartmentName(departmentDetails.getDepartmentName());
        existingDepartment.setDepartmentDescription(departmentDetails.getDepartmentDescription());
        
        return departmentRepository.save(existingDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.findById(id).orElseThrow(() -> 
            new ResourceNotFoundException("معذرت! ڈیلیٹ کرنے کے لیے مطلوبہ ڈیپارٹمنٹ نہیں مل سکا: " + id));
            
        departmentRepository.deleteById(id);
    }

    // --- 6. پیجینیشن اور سرچ کا لاجک (اب MapStruct میپر کے ساتھ) ---
    @Override
    public Page<DepartmentDto> getDepartmentsByPaginationAndSearch(int pageNo, int pageSize, String sortField, String sortDirection, String keyword) {
        
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? 
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        Page<Department> departments;
        
        if (keyword != null && !keyword.trim().isEmpty()) {
            departments = departmentRepository.searchDepartments(keyword, pageable);
        } else {
            departments = departmentRepository.findAll(pageable);
        }
        
        // میپر کا استعمال کر کے ڈیٹا کو ریکارڈ ڈی ٹی او (Record DTO) میں تبدیل کرنا
        return departments.map(departmentMapper::toDto);
    }
}