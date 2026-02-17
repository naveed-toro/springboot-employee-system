package com.istad.employee_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.istad.employee_system.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // سارے بنیادی فنکشنز (Save, Delete, Find) اس میں پہلے سے موجود ہیں۔
    // ہمیں کچھ لکھنے کی ضرورت نہیں!
}