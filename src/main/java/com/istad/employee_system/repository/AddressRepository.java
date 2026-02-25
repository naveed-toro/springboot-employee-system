package com.istad.employee_system.repository;

import com.istad.employee_system.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}