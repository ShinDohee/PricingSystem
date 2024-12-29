package com.example.musinsa.pricingSystem.apiSystem.repository;

import com.example.musinsa.pricingSystem.apiSystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory_CategoryId(Long categoryId);  // 카테고리 ID로 상품 조회


    @Query("SELECT p FROM Product p WHERE p.category.categoryId = :categoryId AND p.brand.brandId = :brandId ORDER BY p.price ASC")
    List<Product> findLowestPriceProductByCategoryAndBrand(@Param("categoryId") Long categoryId, @Param("brandId") Long brandId);

    @Query("SELECT p FROM Product p WHERE p.category.categoryId = :categoryId ORDER BY p.price ASC")
    List<Product> findLowestPriceProductByCategory(@Param("categoryId") Long categoryId);

}