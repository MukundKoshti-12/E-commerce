package com.scaler.productservice.exceptions;

// Below class is custom exception
public class ProductNotFoundException extends Exception {
    private Long productId;
    public ProductNotFoundException(Long productId) {
        this.productId = productId;
    }
    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
