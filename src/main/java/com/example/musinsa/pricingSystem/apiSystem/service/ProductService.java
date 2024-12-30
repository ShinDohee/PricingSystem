package com.example.musinsa.pricingSystem.apiSystem.service;

import com.example.musinsa.pricingSystem.apiSystem.config.PricingSystemException;
import com.example.musinsa.pricingSystem.apiSystem.dto.ProductDto;
import com.example.musinsa.pricingSystem.apiSystem.entity.Brand;
import com.example.musinsa.pricingSystem.apiSystem.entity.Category;
import com.example.musinsa.pricingSystem.apiSystem.entity.Product;
import com.example.musinsa.pricingSystem.apiSystem.repository.BrandRepository;
import com.example.musinsa.pricingSystem.apiSystem.repository.CategoryRepository;
import com.example.musinsa.pricingSystem.apiSystem.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.musinsa.pricingSystem.apiSystem.config.PricingSystemException.ExceptionCode.*;


@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final BrandRepository brandRepository;

    private final CategoryRepository categoryRepository;

    public Product getProduct(@PathVariable Long id) {
        return productRepository.findById(id).orElseThrow(() -> new PricingSystemException(PRODUCT_NOT_FOUND));
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public ResponseEntity<Map<String, Object>> addProduct(ProductDto dto) {
        Map<String, Object> response = new HashMap<>();
        try {
            Brand brand = brandRepository.findById(dto.getBrandId())
                    .orElseThrow(() -> new PricingSystemException(BRAND_NOT_FOUND));
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new PricingSystemException(CATEGORY_NOT_FOUND));

            Product product = new Product();
            product.setBrand(brand);
            product.setCategory(category);
            product.setPrice(dto.getPrice());

            productRepository.save(product);

            response.put("status", "success");
            response.put("message", "Product added successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "failure");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @Transactional
    public void updateProduct(Long productId, ProductDto dto) {
        Map<String, Object> response = new HashMap<>();
        try {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new PricingSystemException(PRODUCT_NOT_FOUND));
            if (dto.getBrandId() != null) {
                Brand brand = brandRepository.findById(dto.getBrandId())
                        .orElseThrow(() -> new PricingSystemException(BRAND_NOT_FOUND));
                product.setBrand(brand);
            }
            if (dto.getCategoryId() != null) {
                Category category = categoryRepository.findById(dto.getCategoryId())
                        .orElseThrow(() -> new PricingSystemException(CATEGORY_NOT_FOUND));
                product.setCategory(category);
            }
            if (dto.getPrice() != null) {
                product.setPrice(dto.getPrice());
            }

            productRepository.save(product);

            response.put("status", "success");
            response.put("message", "Product updated successfully");
            ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "failure");
            response.put("message", e.getMessage());
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    // 상품 삭제
    public ResponseEntity<Map<String, Object>> deleteProduct(Long productId) {
        Map<String, Object> response = new HashMap<>();
        try {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new PricingSystemException(PRODUCT_NOT_FOUND));

            productRepository.delete(product);

            response.put("status", "success");
            response.put("message", "Product deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "failure");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


}