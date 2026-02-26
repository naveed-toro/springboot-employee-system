package com.istad.employee_system.service;

import com.istad.employee_system.dto.DepartmentDto;
import com.istad.employee_system.model.Department;
import org.springframework.data.domain.Page;
import java.util.List;

public interface DepartmentService {
    
    // 1. نیا ڈیپارٹمنٹ بنانا (POST)
    Department createDepartment(Department department);
    
    // 2. تمام ڈیپارٹمنٹس کی لسٹ لانا (GET All)
    List<Department> getAllDepartments();
    
    // 3. کسی خاص ڈیپارٹمنٹ کو ڈھونڈنا (GET by ID)
    Department getDepartmentById(Long id);
    
    // 4. اپ ڈیٹ کرنا (PUT)
    Department updateDepartment(Long id, Department departmentDetails);
    
    // 5. ڈیلیٹ کرنا (DELETE)
    void deleteDepartment(Long id);

    // 6. نیا فیچر: پیجینیشن اور سرچ (اب DepartmentDto کی پلیٹ کے ساتھ)
    Page<DepartmentDto> getDepartmentsByPaginationAndSearch(int pageNo, int pageSize, String sortField, String sortDirection, String keyword);
}