package com.example.musinsa.pricingSystem.apiSystem.service;

import com.example.musinsa.pricingSystem.apiSystem.config.PricingSystemException;
import com.example.musinsa.pricingSystem.apiSystem.dto.BrandDto;
import com.example.musinsa.pricingSystem.apiSystem.entity.Brand;
import com.example.musinsa.pricingSystem.apiSystem.entity.Category;
import com.example.musinsa.pricingSystem.apiSystem.entity.Product;
import com.example.musinsa.pricingSystem.apiSystem.repository.BrandRepository;
import com.example.musinsa.pricingSystem.apiSystem.repository.CategoryRepository;
import com.example.musinsa.pricingSystem.apiSystem.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.example.musinsa.pricingSystem.apiSystem.config.PricingSystemException.ExceptionCode.BRAND_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public Brand getBrand(Long id) {
        return brandRepository.findById(id).orElseThrow(() -> new PricingSystemException(BRAND_NOT_FOUND));
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
            log.error("Brand not found with id: {}", id);
            throw new PricingSystemException(BRAND_NOT_FOUND);
        }
    }

    // Delete a brand
    public void deleteBrand(Long id) {
        if (brandRepository.existsById(id)) {
            brandRepository.deleteById(id);
        } else {
            log.error("Brand not found with id: {}", id);
            throw new PricingSystemException(BRAND_NOT_FOUND);
        }
    }

    public Map<String, Object> getLowestPriceDetails() {
        // Step 1: 모든 브랜드와 카테고리 조회
        List<Brand> brands = brandRepository.findAll();
        List<Category> categories = categoryRepository.findAll();

        Map<String, Integer> brandTotalPrices = new HashMap<>();
        Map<String, List<Map<String, String>>> brandCategoryPrices = new HashMap<>();

        // Step 2: 브랜드별 카테고리 최저가 계산
        for (Brand brand : brands) {
            int totalPrice = 0;
            List<Map<String, String>> categoryPrices = new ArrayList<>();

            for (Category category : categories) {
                // 각 카테고리에서 해당 브랜드의 최저가 상품 찾기
                List<Product> products = productRepository.findLowestPriceProductByCategoryAndBrand(category.getCategoryId(), brand.getBrandId());
                if (!products.isEmpty()) {
                    Product lowestPriceProduct = products.get(0); // 최저가 상품
                    int price = lowestPriceProduct.getPrice();
                    totalPrice += price;

                    categoryPrices.add(Map.of(
                            "카테고리", category.getCategoryName(),
                            "가격", String.format("%,d", price)
                    ));
                }
            }

            // 브랜드별 총액과 카테고리별 최저가 저장
            brandTotalPrices.put(brand.getBrandName(), totalPrice);
            brandCategoryPrices.put(brand.getBrandName(), categoryPrices);
        }

        // Step 3: 총액이 가장 낮은 브랜드 찾기
        String lowestPriceBrand = brandTotalPrices.entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .orElseThrow(() -> new NoSuchElementException("No brand data found"))
                .getKey();

        // Step 4: 결과 구성
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> lowestPriceDetails = new HashMap<>();
        lowestPriceDetails.put("브랜드", lowestPriceBrand);
        lowestPriceDetails.put("카테고리", brandCategoryPrices.get(lowestPriceBrand));
        lowestPriceDetails.put("총액", String.format("%,d", brandTotalPrices.get(lowestPriceBrand)));

        response.put("최저가", lowestPriceDetails);

        return response;
    }

}