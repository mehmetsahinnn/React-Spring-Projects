package com.humanresources.hr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<com.humanresources.hr.models.Manager, Integer> {
}
