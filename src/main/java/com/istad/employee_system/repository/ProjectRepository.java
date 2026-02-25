package com.istad.employee_system.repository;

import com.istad.employee_system.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}