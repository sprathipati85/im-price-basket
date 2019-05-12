package com.pricebasket.bjss.service;

import com.pricebasket.bjss.model.Product;
import com.pricebasket.bjss.model.ShoppingCart;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class PriceBasketServiceTest {

	IPriceBasketService priceBasketService;
	ShoppingCart cart;
	Map<String, Product > products;

	@Before
	public void setup() {
		priceBasketService = new PriceBasketService();
		cart = new ShoppingCart();
		products = new HashMap<>();
		products.put("Apples", new Product("Apples", "1.00"));
		products.put("Milk", new Product("Milk", "1.00"));
	}
	
	@Test
	public void verifytheCommandLineInputIsValid() {
		String[] args = {"Apples", "Milk"};
		boolean isInputValid = priceBasketService.isInputValid(args, cart, products);
		assertTrue(isInputValid);
	}

	@Test
	public void verifytheCartIsNormalized() {
		String[] args = {"Apples", "Milk"};
		cart.setItems(new ArrayList<>());
		priceBasketService.normalizeCart(cart);
	}

}
