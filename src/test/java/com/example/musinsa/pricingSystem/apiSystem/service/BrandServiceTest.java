package com.example.musinsa.pricingSystem.apiSystem.service;

import com.example.musinsa.pricingSystem.apiSystem.config.PricingSystemException;
import com.example.musinsa.pricingSystem.apiSystem.dto.BrandDto;
import com.example.musinsa.pricingSystem.apiSystem.entity.Brand;
import com.example.musinsa.pricingSystem.apiSystem.repository.BrandRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@Rollback(false)
@SpringBootTest
class BrandServiceTest {

    @Autowired
    private BrandService brandService;

    @Autowired
    private BrandRepository brandRepository;

    @Test
    public void 브랜드를_가져온다() {
        Brand brand = new Brand();
        brand.setBrandId(1L);
        brand.setBrandName("Test Brand");
        brandRepository.save(brand);
        Brand foundBrand = brandService.getBrand(1L);
        assertThat(foundBrand.getBrandName()).isEqualTo("Test Brand");
    }

    @Test
    void 브랜드_목록을_가져온다() {
        List<Brand> all = brandService.getBrands();
        assertThat(all).hasSize(10);
    }

    @Test
    void 브랜드를_추가한다() {
        BrandDto brandDto = new BrandDto();
        brandDto.setBrandName("New Brand");
        Brand savedBrand = brandService.addBrand(brandDto);
        assertThat(savedBrand.getBrandName()).isEqualTo("New Brand");
    }

    @Test
    void 브랜드를_수정한다() {
        BrandDto brandDto = new BrandDto();
        brandDto.setBrandName("Updated Brand");
        Brand updatedBrand = brandService.updateBrand(1L, brandDto);
        assertThat(updatedBrand.getBrandName()).isEqualTo("Updated Brand");
    }

    @Test
    void 브랜드를_삭제한다() {
        BrandDto brandDto = new BrandDto();
        brandDto.setBrandName("New Brand");
        Brand savedBrand = brandService.addBrand(brandDto);
        brandService.deleteBrand(savedBrand.getBrandId());
        assertThatThrownBy(() -> brandService.getBrand(savedBrand.getBrandId())).isInstanceOf(PricingSystemException.class);
    }
}