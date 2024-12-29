package com.example.musinsa.pricingSystem.apiSystem.controller;

import com.example.musinsa.pricingSystem.apiSystem.dto.ProductDto;
import com.example.musinsa.pricingSystem.apiSystem.entity.Product;
import com.example.musinsa.pricingSystem.apiSystem.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Resource( name = "productService")
    private ProductService productService;

    @PostMapping("/all")
    public List<Product> getProducts() {
        return productService.getProducts();
    }
    @PostMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateProduct(@RequestParam Long productID , @RequestBody ProductDto productDto) {
        // 커스텀 검증 로직을 컨트롤러에서 처리
        if (!isValidForModiGroup(productDto)) {
            return ResponseEntity.badRequest().body("세 필드 중 적어도 하나는 필수입니다.");
        }
        productService.updateProduct(productID, productDto);
        // 상품 수정 로직
        return ResponseEntity.ok("Product updated successfully");
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addProduct(@Validated(ProductDto.AddGroup.class) @RequestBody ProductDto productDto ) {
        return productService.addProduct(productDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    // Controller에서 검증 메서드 호출
    private boolean isValidForModiGroup(ProductDto productDto) {
        return productDto.getBrandId() != null || productDto.getCategoryId() != null || productDto.getPrice() != null;
    }
}
