package com.example.musinsa.pricingSystem.apiSystem.repository;

import com.example.musinsa.pricingSystem.apiSystem.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {
  Optional<Brand> findByBrandId(Long id);
  }