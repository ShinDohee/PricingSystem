package com.example.musinsa.pricingSystem.apiSystem.repository;

import com.example.musinsa.pricingSystem.apiSystem.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
  }