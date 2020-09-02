package com.practice.employee.Repository;

import com.practice.employee.Entity.employeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface employeeRepo extends JpaRepository<employeeEntity, Long> {
}
