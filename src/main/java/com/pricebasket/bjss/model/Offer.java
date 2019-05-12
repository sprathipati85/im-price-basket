package com.pricebasket.bjss.model;

import java.util.List;

public class Offer {

    private String productName;

    private String productPrice;

    private String productQuantity;

    private String discountProductName;
    private String discountProductQuantity;
    private String discountProductPrice;

    public Offer() {
    }

    public Offer(String productName, String productPrice, String productQuantity, String discountProductName, String discountProductQuantity, String discountProductPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.discountProductName = discountProductName;
        this.discountProductQuantity = discountProductQuantity;
        this.discountProductPrice = discountProductPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getDiscountProductName() {
        return discountProductName;
    }

    public void setDiscountProductName(String discountProductName) {
        this.discountProductName = discountProductName;
    }

    public String getDiscountProductQuantity() {
        return discountProductQuantity;
    }

    public void setDiscountProductQuantity(String discountProductQuantity) {
        this.discountProductQuantity = discountProductQuantity;
    }

    public String getDiscountProductPrice() {
        return discountProductPrice;
    }

    public void setDiscountProductPrice(String discountProductPrice) {
        this.discountProductPrice = discountProductPrice;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "productName='" + productName + '\'' +
                ", productPrice='" + productPrice + '\'' +
                ", productQuantity='" + productQuantity + '\'' +
                ", discountProductName='" + discountProductName + '\'' +
                ", discountProductQuantity='" + discountProductQuantity + '\'' +
                ", discountProductPrice='" + discountProductPrice + '\'' +
                '}';
    }
}
