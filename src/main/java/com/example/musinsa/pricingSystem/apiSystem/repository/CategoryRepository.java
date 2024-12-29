package com.example.musinsa.pricingSystem.apiSystem.repository;

import com.example.musinsa.pricingSystem.apiSystem.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}