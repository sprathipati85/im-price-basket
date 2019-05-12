package com.pricebasket.bjss.model;

import java.math.BigDecimal;

public class PriceBasketResponse {

    private BigDecimal totalBeforeDiscount;
    private BigDecimal discountedPrice;
    private String discountsApplied;
    private BigDecimal totalAfterDiscount;

    public BigDecimal getTotalAfterDiscount() {
        return totalAfterDiscount;
    }

    public void setTotalAfterDiscount(BigDecimal totalAfterDiscount) {
        this.totalAfterDiscount = totalAfterDiscount;
    }

    public BigDecimal getTotalBeforeDiscount() {
        return totalBeforeDiscount;
    }

    public void setTotalBeforeDiscount(BigDecimal totalBeforeDiscount) {
        this.totalBeforeDiscount = totalBeforeDiscount;
    }

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public String getDiscountsApplied() {
        return discountsApplied;
    }

    public void setDiscountsApplied(String discountsApplied) {
        this.discountsApplied = discountsApplied;
    }

    @Override
    public String toString() {
        return "PriceBasketResponse{" +
                "totalBeforeDiscount=" + totalBeforeDiscount +
                ", discountedPrice=" + discountedPrice +
                ", discountsApplied='" + discountsApplied + '\'' +
                ", totalAfterDiscount=" + totalAfterDiscount +
                '}';
    }
}
