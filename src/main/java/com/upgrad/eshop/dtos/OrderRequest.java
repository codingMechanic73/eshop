package com.upgrad.eshop.dtos;

public class OrderRequest {
    private Long addressId;
    private Long productId;

    public OrderRequest() {
    }

    public OrderRequest(Long addressId, Long productId) {
        this.addressId = addressId;
        this.productId = productId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
