package com.pricebasket.bjss.service;

import com.pricebasket.bjss.model.Product;
import com.pricebasket.bjss.model.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface IPriceBasketService {

    void normalizeCart(ShoppingCart cart);

    boolean isInputValid(String[] args, ShoppingCart cart, Map<String, Product> products);
}
