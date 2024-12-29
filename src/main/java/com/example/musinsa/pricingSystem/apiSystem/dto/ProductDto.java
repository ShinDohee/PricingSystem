package com.example.musinsa.pricingSystem.apiSystem.dto;

import com.example.musinsa.pricingSystem.apiSystem.entity.Product;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link Product}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto implements Serializable {

    @NotNull(groups = {ProductDto.AddGroup.class}, message = "브랜드명은 추가시 필수입니다.")
    Long brandId;

    @NotNull(groups = {ProductDto.AddGroup.class}, message = "카테고리 코드는 추가시 필수입니다.")
    Long categoryId;

    @NotNull(groups = {ProductDto.AddGroup.class}, message = "가격은 추가시 필수입니다.")
    Integer price;


    // Validation groups
    public interface AddGroup {}
}