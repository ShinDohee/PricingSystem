package com.example.musinsa.pricingSystem.apiSystem.service;

import com.example.musinsa.pricingSystem.apiSystem.dto.ProductDto;
import com.example.musinsa.pricingSystem.apiSystem.entity.Brand;
import com.example.musinsa.pricingSystem.apiSystem.entity.Category;
import com.example.musinsa.pricingSystem.apiSystem.entity.Product;
import com.example.musinsa.pricingSystem.apiSystem.repository.BrandRepository;
import com.example.musinsa.pricingSystem.apiSystem.repository.CategoryRepository;
import com.example.musinsa.pricingSystem.apiSystem.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Product getProduct(@PathVariable Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public ResponseEntity<Map<String, Object>> addProduct(ProductDto dto) {
        Map<String, Object> response = new HashMap<>();
        try {
            Brand brand = brandRepository.findById(dto.getBrandId())
                    .orElseThrow(() -> new RuntimeException("Brand not found"));
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));

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
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            if (dto.getBrandId() != null) {
                Brand brand = brandRepository.findById(dto.getBrandId())
                        .orElseThrow(() -> new RuntimeException("Brand not found"));
                product.setBrand(brand);
            }
            if (dto.getCategoryId() != null) {
                Category category = categoryRepository.findById(dto.getCategoryId())
                        .orElseThrow(() -> new RuntimeException("Category not found"));
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
                    .orElseThrow(() -> new RuntimeException("Product not found"));

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