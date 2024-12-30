package com.example.musinsa.pricingSystem.apiSystem.service;

import com.example.musinsa.pricingSystem.apiSystem.config.PricingSystemException;
import com.example.musinsa.pricingSystem.apiSystem.dto.ProductDto;
import com.example.musinsa.pricingSystem.apiSystem.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@Rollback(false)
@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void 상품을_가져온다() {
        Product foundProduct = productService.getProduct(1L);
        assertThat(foundProduct).isNotNull();
    }

    @Test
    void 상품을_모두_가져온다() {
        List<Product> products = productService.getProducts();
        assertThat(products).size().isEqualTo(72);
    }


    @Test
    void 상품을_추가한다() {
        ProductDto productDto = new ProductDto();
        productDto.setBrandId(1L);
        productDto.setCategoryId(1L);
        productDto.setPrice(10000);

        ResponseEntity<Map<String, Object>> mapResponseEntity = productService.addProduct(productDto);
        System.out.println(mapResponseEntity);
    }

    @Test
    void 상품을_삭제한다() {
        productService.deleteProduct(1L);
        assertThatThrownBy(() -> productService.getProduct(1L)).isInstanceOf(PricingSystemException.class);
    }

}