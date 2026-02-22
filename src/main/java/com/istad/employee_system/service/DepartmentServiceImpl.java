package com.istad.employee_system.service;

import com.istad.employee_system.exception.ResourceNotFoundException;
import com.istad.employee_system.model.Department;
import com.istad.employee_system.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    // Constructor Injection (بہترین طریقہ)
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

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
        // اگر ڈیپارٹمنٹ نہ ملے تو سیکیورٹی گارڈ بلائیں
        return departmentRepository.findById(id).orElseThrow(() -> 
            new ResourceNotFoundException("معذرت! اس آئی ڈی کے ساتھ کوئی ڈیپارٹمنٹ موجود نہیں ہے: " + id));
    }

    @Override
    public Department updateDepartment(Long id, Department departmentDetails) {
        // پہلے چیک کریں کہ ڈیپارٹمنٹ موجود ہے یا نہیں
        Department existingDepartment = departmentRepository.findById(id).orElseThrow(() -> 
            new ResourceNotFoundException("معذرت! اپ ڈیٹ کے لیے اس آئی ڈی کا ڈیپارٹمنٹ نہیں ملا: " + id));
        
        // نیا ڈیٹا سیٹ کریں
        existingDepartment.setDepartmentName(departmentDetails.getDepartmentName());
        existingDepartment.setDepartmentDescription(departmentDetails.getDepartmentDescription());
        
        // سیو کریں
        return departmentRepository.save(existingDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {
        // چیک کریں کہ موجود ہے یا نہیں
        departmentRepository.findById(id).orElseThrow(() -> 
            new ResourceNotFoundException("معذرت! ڈیلیٹ کرنے کے لیے مطلوبہ ڈیپارٹمنٹ نہیں مل سکا: " + id));
            
        departmentRepository.deleteById(id);
    }
}