package com.pricebasket.bjss.model;

import java.util.List;

public class ShoppingCart {

    private List<ProductItem> items;

    public List<ProductItem> getItems() {
        return items;
    }

    public void setItems(List<ProductItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "items=" + items +
                '}';
    }
}
