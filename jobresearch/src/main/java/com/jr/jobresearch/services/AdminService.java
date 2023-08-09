package com.jr.jobresearch.services;

import com.jr.jobresearch.models.Admin;
import com.jr.jobresearch.repositories.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin findAdminByUsername(String username) {
        return adminRepository.findByUsername(username);
    }
}
