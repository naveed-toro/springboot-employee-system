package com.istad.employee_system.service;

import com.istad.employee_system.dto.EmployeeDto;
import com.istad.employee_system.exception.ResourceNotFoundException;
import com.istad.employee_system.mapper.EmployeeMapper;
import com.istad.employee_system.model.Employee;
import com.istad.employee_system.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // لومبوک تمام final فیلڈز کے لیے خود بخود کنسٹرکٹر بنا دے گا
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper; // میپر کو یہاں انجیکٹ کیا گیا ہے

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
            new ResourceNotFoundException("ایمپلوئی اس آئی ڈی کے ساتھ موجود نہیں ہے: " + id));
    }

    @Override
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> 
            new ResourceNotFoundException("اپ ڈیٹ کے لیے ایمپلوئی نہیں ملا، آئی ڈی: " + id));

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmail(employeeDetails.getEmail());
        
        // ڈیپارٹمنٹ اور پوزیشن سیٹ کرنا
        employee.setDepartment(employeeDetails.getDepartment());
        employee.setPosition(employeeDetails.getPosition());

        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> 
            new ResourceNotFoundException("ڈیلیٹ کرنے کے لیے ایمپلوئی نہیں ملا، آئی ڈی: " + id));
        employeeRepository.delete(employee);
    }

    // --- نیا فیچر: پیجینیشن، سارٹنگ اور سرچ (اب MapStruct میپر کے ساتھ) ---
    @Override
    public Page<EmployeeDto> getEmployeesByPaginationAndSearch(int pageNo, int pageSize, String sortField, String sortDirection, String keyword) {
        
        // 1. ترتیب (Sorting) سیٹ کریں
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? 
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        // 2. پیجینیشن کی معلومات سیٹ کریں
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        Page<Employee> employees;
        
        // 3. کی ورڈ کے ساتھ سرچ کریں یا سارا ڈیٹا لائیں
        if (keyword != null && !keyword.trim().isEmpty()) {
            employees = employeeRepository.searchEmployees(keyword, pageable);
        } else {
            employees = employeeRepository.findAll(pageable);
        }
        
        // 4. اب دستی میپنگ کے بجائے میپر کا استعمال کرتے ہوئے ڈیٹا (Records) واپس کریں
        return employees.map(employeeMapper::toDto);
    }
}