package com.humanresources.hr.repositories;

import com.humanresources.hr.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Employee, Long> {
    Boolean existsByName(String username);
    Employee findByName(String username);
}
