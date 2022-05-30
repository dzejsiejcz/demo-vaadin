package com.example.demovaadin.backend.repository;

import com.example.demovaadin.backend.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
