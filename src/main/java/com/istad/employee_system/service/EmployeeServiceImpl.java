package com.istad.employee_system.service;

import com.istad.employee_system.exception.ResourceNotFoundException;
import com.istad.employee_system.model.Employee;
import com.istad.employee_system.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> 
            new ResourceNotFoundException("Employee not exist with id :" + id));
    }

    @Override
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> 
            new ResourceNotFoundException("Employee not exist with id :" + id));

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmail(employeeDetails.getEmail());
        
        // اگر ہم چاہیں تو یہاں ڈیپارٹمنٹ اور باقی چیزیں بھی اپڈیٹ کروا سکتے ہیں
        employee.setDepartment(employeeDetails.getDepartment());

        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> 
            new ResourceNotFoundException("Employee not exist with id :" + id));
        employeeRepository.delete(employee);
    }

    // --- نیا فیچر: پیجینیشن اور سرچ کا اصل لاجک ---
    @Override
    public Page<Employee> getEmployeesByPaginationAndSearch(int pageNo, int pageSize, String sortField, String sortDirection, String keyword) {
        
        // 1. ترتیب (Sorting) سیٹ کریں: سیدھی (ASC) یا الٹی (DESC)
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? 
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        // 2. پیج (Pageable) سیٹ کریں (سپرنگ بوٹ میں پیج 0 سے شروع ہوتا ہے، اس لیے ہم نے pageNo - 1 کیا ہے)
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        // 3. اگر کوئی کی ورڈ (keyword) دیا گیا ہے تو سرچ کریں، ورنہ سارے لائیں
        if (keyword != null && !keyword.trim().isEmpty()) {
            return employeeRepository.searchEmployees(keyword, pageable);
        }
        
        return employeeRepository.findAll(pageable);
    }
}