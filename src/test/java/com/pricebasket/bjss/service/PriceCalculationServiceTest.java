package com.pricebasket.bjss.service;

import com.pricebasket.bjss.model.DiscountRule;
import com.pricebasket.bjss.model.Offer;
import com.pricebasket.bjss.model.PriceBasketResponse;
import com.pricebasket.bjss.model.Product;
import com.pricebasket.bjss.model.ProductItem;
import com.pricebasket.bjss.model.ShoppingCart;
import com.pricebasket.bjss.service.calculate.IPriceCalculationService;
import com.pricebasket.bjss.service.calculate.PriceCalculationService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PriceCalculationServiceTest {

	IPriceCalculationService priceCalculationService;
	ShoppingCart cart;
	List<ProductItem> items;
	ProductItem productItem;
	Map<String, Product> products;

	@Before
	public void setup() {
		priceCalculationService = new PriceCalculationService();
		cart = new ShoppingCart();
		items = new ArrayList<>();
		productItem = new ProductItem("Apples", "1");
		items.add(productItem);
		cart.setItems(items);
		products = new HashMap<>();
		Product product = new Product("Apples", "2.00");
		Offer offer = new Offer("Apples", "1", "1", "Apples", "10", "10");
		product.setDiscountOffers(Arrays.asList(offer));
		products.put("Apples", product);
	}

	@Test
	public void verifyTheApplicableDiscountRulesAreGenerated() throws IOException {
		List<DiscountRule> discountRules = priceCalculationService.generateApplicableDiscountRules(cart, products);
		assertNotNull(discountRules);
		assertEquals(discountRules.get(0).getProductname(), "Apples");
		assertEquals(discountRules.get(0).getDiscountPercent(), "10");
	}

	@Test
	public void verifyTheCalculationPrice() throws IOException {
		List<DiscountRule> discountRules = priceCalculationService.generateApplicableDiscountRules(cart, products);
		PriceBasketResponse response = priceCalculationService.calculateTotalPrice(products, cart, discountRules);
		assertNotNull(discountRules);
		assertEquals(discountRules.get(0).getProductname(), "Apples");
		assertEquals(discountRules.get(0).getDiscountPercent(), "10");
		assertNotNull(response);
		assertEquals(response.getDiscountsApplied(), "Apples - 10% off: 0.20 ");
		assertEquals(response.getTotalBeforeDiscount(), new BigDecimal("2.00"));
		assertEquals(response.getTotalAfterDiscount(), new BigDecimal("1.80"));


	}
}
