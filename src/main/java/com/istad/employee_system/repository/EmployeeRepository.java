package com.istad.employee_system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.istad.employee_system.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // یہ کیوری (Query) ایمپلائی کو First Name یا Last Name کے ذریعے سرچ کرے گی
    // اور ساتھ ہی اسے پیجز (Pagination) میں بھی بانٹ دے گی
    @Query("SELECT e FROM Employee e WHERE LOWER(e.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(e.lastName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Employee> searchEmployees(@Param("keyword") String keyword, Pageable pageable);

}