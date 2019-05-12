package com.pricebasket.bjss.model;

public class DiscountRule {

    private String productname;

    private String discountPercent;

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(String discountPercent) {
        this.discountPercent = discountPercent;
    }

    @Override
    public String toString() {
        return "DiscountRule{" +
                "productname='" + productname + '\'' +
                ", discountPercent='" + discountPercent + '\'' +
                '}';
    }
}
