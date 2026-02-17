package com.istad.employee_system.service;

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
        // اگر نہ ملے تو ایرر دے گا (اسے ہم بعد میں ہینڈل کریں گے)
        return employeeRepository.findById(id).orElseThrow();
    }

    @Override
    public Employee updateEmployee(Employee employee, Long id) {
        // پہلے چیک کرو کہ بندہ موجود ہے یا نہیں
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow();
        
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
        employeeRepository.findById(id).orElseThrow();
        employeeRepository.deleteById(id);
    }
}