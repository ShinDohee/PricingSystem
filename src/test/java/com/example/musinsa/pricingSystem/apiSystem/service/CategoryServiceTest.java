package com.example.musinsa.pricingSystem.apiSystem.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@Rollback(false)
@SpringBootTest
class CategoryServiceTest {
    @Autowired
    private CategoryService categoryService;

    @Test
    void 카테고리_최저가_상세() {

        var response = categoryService.getCategoryLowestPriceDetails();

        assertThat(response.size()).isEqualTo(2);
    }
}