package com.cv.cv.repositories;

import com.cv.cv.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeRepository extends JpaRepository<User, Long> {
}