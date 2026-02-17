package com.istad.employee_system.service;

import com.istad.employee_system.model.Employee;
import java.util.List;

public interface EmployeeService {
    // 1. نیا ایمپلائی محفوظ کریں
    Employee saveEmployee(Employee employee);

    // 2. تمام ایمپلائیز کی لسٹ دیکھیں
    List<Employee> getAllEmployees();

    // 3. کسی ایک ایمپلائی کو ID سے ڈھونڈیں
    Employee getEmployeeById(Long id);

    // 4. ایمپلائی کا ڈیٹا اپ ڈیٹ کریں
    Employee updateEmployee(Employee employee, Long id);

    // 5. ایمپلائی کو ڈیلیٹ کریں
    void deleteEmployee(Long id);
}