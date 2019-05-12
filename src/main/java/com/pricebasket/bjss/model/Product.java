package com.pricebasket.bjss.model;

import com.pricebasket.bjss.exception.ProductNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Product {
    private String productName;

    private String productPrice;

    private List<Offer> discountOffers;

    public Product() {
    }

    public Product(String productName) {
        this.productName = productName;
    }

    public Product(String productName, String productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
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

    public List<Offer> getDiscountOffers() {
        return discountOffers == null ? new ArrayList<Offer>() : discountOffers;
    }

    public void setDiscountOffers(List<Offer> discountOffers) {
        this.discountOffers = discountOffers;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", productPrice='" + productPrice + '\'' +
                ", discountOffers=" + discountOffers +
                '}';
    }

    public static String findProductByName(String product, Map<String, Product> products) throws ProductNotFoundException {
        Product foundProduct = products.getOrDefault(product, null);
        if (foundProduct == null) {
            throw new ProductNotFoundException(String.format("could not find product %s", product));
        }
        return foundProduct.getProductName();
    }
}
