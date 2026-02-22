package com.istad.employee_system.repository;

import com.istad.employee_system.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

// ہم نے JpaRepository کو بتا دیا کہ یہ Department کے لیے منشی ہے جس کی ID Long (نمبر) ہے
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}