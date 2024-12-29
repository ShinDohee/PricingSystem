package com.example.musinsa.pricingSystem.apiSystem.controller;

import com.example.musinsa.pricingSystem.apiSystem.dto.CategoryDto;
import com.example.musinsa.pricingSystem.apiSystem.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    // 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 조회
    @PostMapping("/price-range")
    public ResponseEntity< Map<String, Object>> getPriceRangeByCategory(@RequestBody CategoryDto categoryDto) {
        Map<String, Object> result = categoryService.getPriceRangeByCategory(categoryDto);
        if (result == null) {
            return ResponseEntity.notFound().build();  // 카테고리가 없으면 404 반환
        }
        return ResponseEntity.ok(result);
    }
}
