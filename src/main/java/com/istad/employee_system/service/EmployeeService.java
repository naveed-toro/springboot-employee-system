package com.istad.employee_system.service;

import com.istad.employee_system.dto.EmployeeDto;
import com.istad.employee_system.model.Employee;
import org.springframework.data.domain.Page;
import java.util.List;

public interface EmployeeService {
    
    // پرانے بنیادی کام (CRUD)
    Employee createEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    Employee updateEmployee(Long id, Employee employeeDetails);
    void deleteEmployee(Long id);

    // نیا فیچر: پیجینیشن (Pagination)، سارٹنگ (Sorting) اور سرچ (Search)
    // اپڈیٹ: اب ہم Employee کی بجائے EmployeeDto (پلیٹ) کا پیج واپس کریں گے
    Page<EmployeeDto> getEmployeesByPaginationAndSearch(int pageNo, int pageSize, String sortField, String sortDirection, String keyword);
}