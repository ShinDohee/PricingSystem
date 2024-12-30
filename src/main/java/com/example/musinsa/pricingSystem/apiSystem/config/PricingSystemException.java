package com.example.musinsa.pricingSystem.apiSystem.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class PricingSystemException extends RuntimeException {

    public PricingSystemException(ExceptionCode exceptionCode) {
        super(exceptionCode.message);
    }

    @Getter
    @AllArgsConstructor
    public enum ExceptionCode {
        STATUS_SUCCESS("PS0000", "성공하였습니다"),
        STATUS_UNKNOWN_ERROR("PS1000", "오류가 발생했습니다. 잠시후 다시 시도해 주세요."),
        STATUS_BAD_REQUEST("PS1001", "잘못된 요청입니다."),
        CATEGORY_NOT_FOUND("PS2000", "카테고리를 찾을수 없습니다."),
        PRODUCT_NOT_FOUND("PS2001", "상품을 찾을수 없습니다."),
        BRAND_NOT_FOUND("PS2002", "브랜드를 찾을수 없습니다.");
        private String code;
        private String message;
    }
}


