package com.example.musinsa.pricingSystem.apiSystem.controller;
import com.example.musinsa.pricingSystem.apiSystem.dto.BrandDto;
import com.example.musinsa.pricingSystem.apiSystem.entity.Brand;
import com.example.musinsa.pricingSystem.apiSystem.service.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.*;
@RestController
@RequestMapping("/api/brand")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/all")
    public List<Brand> getAllBrands() {
        return brandService.getBrands(); // 데이터 반환
    }
    @GetMapping("/{id}")
    public Brand getBrand(@PathVariable Long id) {
        return brandService.getBrand(id); // 데이터 반환
    }

    @PostMapping("/add")
    public ResponseEntity<Brand> addBrand(@Valid @RequestBody BrandDto brandDto) {
        Brand createdBrand = brandService.addBrand(brandDto);
        return ResponseEntity.ok(createdBrand);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Brand> updateBrand(@PathVariable Long id, @Valid @RequestBody BrandDto brandDto) {
        Brand updatedBrand = brandService.updateBrand(id, brandDto);
        return ResponseEntity.ok(updatedBrand);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
        return ResponseEntity.ok().build();
    }

}
