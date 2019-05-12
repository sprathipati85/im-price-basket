package com.pricebasket.bjss.service;

import com.pricebasket.bjss.exception.ProductNotFoundException;
import com.pricebasket.bjss.model.Product;
import com.pricebasket.bjss.model.ProductItem;
import com.pricebasket.bjss.model.ShoppingCart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/*
* Service to process the input data
* Normalizes by validating the input and processing them into valid Products
*/

@Service
public class PriceBasketService implements IPriceBasketService{
    private static Logger LOGGER = LoggerFactory.getLogger(PriceBasketService.class);


    @Override
    public boolean isInputValid(String[] args, ShoppingCart cart, Map<String, Product> products) {
        if(args.length > 0 ) {
            for (String arg : args) {
                System.out.println(arg);
            }
            this.generateProductItemsFromCart(args, cart, products);
        }
        return args.length > 0;
    }


    private void generateProductItemsFromCart(String[] args, ShoppingCart cart, Map<String, Product> products) {
        List<ProductItem> items = new ArrayList<>();
        for(String arg : args){
            ProductItem productItem;
            try {
                productItem = new ProductItem(Product.findProductByName(arg, products));
                items.add(productItem);
            } catch (ProductNotFoundException e) {
                LOGGER.error("Please insert valid input");
                System.exit(0);
            }
        }
        cart.setItems(items);
    }


    @Override
    public void normalizeCart(ShoppingCart cart) {
        this.calculateQuantity(cart);
        Iterator<ProductItem> it = cart.getItems().iterator();
        while (it.hasNext()) {
            ProductItem productItem = it.next();
            if(productItem.getProductQuantity() == null) {
                it.remove();
            }
        }
    }

    private void calculateQuantity(ShoppingCart cart){
        Set<ProductItem> productItem = new HashSet<>(cart.getItems());
        for (ProductItem product : productItem) {
            product.setProductQuantity(String.valueOf(Collections.frequency(cart.getItems(),
                    product)));
        }
        cart.getItems().stream().forEach(System.out::print);
    }
}
