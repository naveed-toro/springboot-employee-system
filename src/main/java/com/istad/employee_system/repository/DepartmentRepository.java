package com.istad.employee_system.repository;

import com.istad.employee_system.model.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// ہم نے JpaRepository کو بتا دیا کہ یہ Department کے لیے منشی ہے جس کی ID Long (نمبر) ہے
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    
    // --- نیا فیچر: ڈیپارٹمنٹ کو نام سے سرچ کرنے کے لیے کسٹم کوری (Query) ---
    // یہ کیوری ڈیپارٹمنٹ کو اس کے نام (departmentName) کے ذریعے ڈھونڈے گی اور اسے پیجز (Pagination) میں بھی بانٹ دے گی
    @Query("SELECT d FROM Department d WHERE LOWER(d.departmentName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Department> searchDepartments(@Param("keyword") String keyword, Pageable pageable);
}