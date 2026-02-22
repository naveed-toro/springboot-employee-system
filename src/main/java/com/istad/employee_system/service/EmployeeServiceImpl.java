package com.istad.employee_system.service;

// ہم نے اپنا ایکسیپشن امپورٹ کر لیا
import com.istad.employee_system.exception.ResourceNotFoundException;
import com.istad.employee_system.model.Employee;
import com.istad.employee_system.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    // Constructor Injection (بہترین طریقہ)
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        // یہاں ہم نے خالی .orElseThrow() کے اندر اپنا گارڈ بٹھا دیا ہے
        return employeeRepository.findById(id).orElseThrow(() -> 
            new ResourceNotFoundException("معذرت! اس آئی ڈی کے ساتھ کوئی ایمپلائی موجود نہیں ہے: " + id));
    }

    @Override
    public Employee updateEmployee(Employee employee, Long id) {
        // پہلے چیک کرو کہ بندہ موجود ہے یا نہیں، اگر نہیں تو ایرر دو
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> 
            new ResourceNotFoundException("معذرت! اپ ڈیٹ کے لیے اس آئی ڈی کا ایمپلائی نہیں ملا: " + id));
        
        // نیا ڈیٹا سیٹ کرو
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        
        // سیو کرو
        return employeeRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        // چیک کرو کہ موجود ہے یا نہیں
        employeeRepository.findById(id).orElseThrow(() -> 
            new ResourceNotFoundException("معذرت! ڈیلیٹ کرنے کے لیے مطلوبہ ایمپلائی نہیں مل سکا: " + id));
            
        employeeRepository.deleteById(id);
    }
}