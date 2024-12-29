package com.example.musinsa.pricingSystem.apiSystem.service;
import com.example.musinsa.pricingSystem.apiSystem.dto.CategoryDto;
import com.example.musinsa.pricingSystem.apiSystem.entity.Brand;
import com.example.musinsa.pricingSystem.apiSystem.entity.Category;
import com.example.musinsa.pricingSystem.apiSystem.entity.Product;
import com.example.musinsa.pricingSystem.apiSystem.repository.BrandRepository;
import com.example.musinsa.pricingSystem.apiSystem.repository.CategoryRepository;
import com.example.musinsa.pricingSystem.apiSystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CategoryService {
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    // 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 조회
    @Transactional
    public Map<String, Object> getPriceRangeByCategory(CategoryDto categoryDto) {
        // 카테고리명으로 카테고리 ID 조회
        Category category = categoryRepository.findByCategoryName(categoryDto.getCategoryname());
        List<Product> products = productRepository.findByCategory_CategoryId(category.getCategoryId());

        if (products.isEmpty()) {
            return Collections.singletonMap("message", "해당 카테고리에 상품이 없습니다."); // 상품이 없을 경우 메시지 반환
        }

        // 최저 가격과 최고 가격 상품 찾기
        Product minPriceProduct = products.stream().min(Comparator.comparingInt(Product::getPrice)).orElse(null);
        Product maxPriceProduct = products.stream().max(Comparator.comparingInt(Product::getPrice)).orElse(null);

        // 최저 가격 브랜드 및 상품 정보
        Optional<Brand> minPriceBrandOpt = brandRepository.findById(minPriceProduct.getBrand().getBrandId());
        Optional<Brand> maxPriceBrandOpt = brandRepository.findById(maxPriceProduct.getBrand().getBrandId());

        // 결과 데이터 생성
        Map<String, Object> response = new HashMap<>();
        response.put("카테고리", categoryDto.getCategoryname());

        List<Map<String, String>> minPriceList = new ArrayList<>();
        if (minPriceBrandOpt.isPresent()) {
            Map<String, String> minPriceMap = new HashMap<>();
            minPriceMap.put("브랜드", minPriceBrandOpt.get().getBrandName());
            minPriceMap.put("가격", String.format("%,d", minPriceProduct.getPrice())); // 가격을 천 단위로 포맷팅
            minPriceList.add(minPriceMap);
        }
        response.put("최저가", minPriceList);

        List<Map<String, String>> maxPriceList = new ArrayList<>();
        if (maxPriceBrandOpt.isPresent()) {
            Map<String, String> maxPriceMap = new HashMap<>();
            maxPriceMap.put("브랜드", maxPriceBrandOpt.get().getBrandName());
            maxPriceMap.put("가격", String.format("%,d", maxPriceProduct.getPrice())); // 가격을 천 단위로 포맷팅
            maxPriceList.add(maxPriceMap);
        }
        response.put("최고가", maxPriceList);

        return response;
    }
}
