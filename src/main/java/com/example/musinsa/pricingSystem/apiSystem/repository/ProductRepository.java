package com.example.musinsa.pricingSystem.apiSystem.repository;

import com.example.musinsa.pricingSystem.apiSystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}