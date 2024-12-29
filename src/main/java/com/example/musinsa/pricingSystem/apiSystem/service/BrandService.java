package com.example.musinsa.pricingSystem.apiSystem.service;
import com.example.musinsa.pricingSystem.apiSystem.dto.BrandDto;
import com.example.musinsa.pricingSystem.apiSystem.entity.Brand;
import com.example.musinsa.pricingSystem.apiSystem.repository.BrandRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public Brand getBrand(Long id) {
        return brandRepository.findById(id).orElseThrow();
    }
    public List<Brand> getBrands() {
        return brandRepository.findAll();
    }
    @Transactional
    public Brand addBrand(BrandDto brandDto) {
        Brand brand = new Brand();
        brand.setBrandName(brandDto.getBrandName());
        System.out.println("Saving brand: " + brand.getBrandName());
        return brandRepository.save(brand);
    }

    @Transactional
    public Brand updateBrand(Long id, BrandDto brandDto) {
        Optional<Brand> existingBrandOpt = brandRepository.findById(id);
        if (existingBrandOpt.isPresent()) {
            Brand existingBrand = existingBrandOpt.get();
            existingBrand.setBrandName(brandDto.getBrandName());
            // Update other fields as needed
            return brandRepository.save(existingBrand);
        } else {
            throw new RuntimeException("Brand not found with id " + id); // Handle the case where the brand doesn't exist
        }
    }

    // Delete a brand
    public void deleteBrand(Long id) {
        if (brandRepository.existsById(id)) {
            brandRepository.deleteById(id);
        } else {
            throw new RuntimeException("Brand not found with id " + id); // Handle the case where the brand doesn't exist
        }
    }

}