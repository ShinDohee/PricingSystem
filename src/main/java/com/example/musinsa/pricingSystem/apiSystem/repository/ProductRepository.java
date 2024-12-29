package com.example.musinsa.pricingSystem.apiSystem.repository;

import com.example.musinsa.pricingSystem.apiSystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory_CategoryId(Long categoryId);  // 카테고리 ID로 상품 조회
}