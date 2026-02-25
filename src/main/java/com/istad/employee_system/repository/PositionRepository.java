package com.istad.employee_system.repository;

import com.istad.employee_system.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
}