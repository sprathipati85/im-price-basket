package com.pricebasket.bjss.model;

import com.pricebasket.bjss.exception.ProductNotFoundException;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ProductTest {

	@Test
	public void verifyTheValuesOfProductItem() {
		Product product = new Product("Apple", "2.00");
		Assert.assertEquals("Apple", product.getProductName());
		Assert.assertEquals("2.00", product.getProductPrice());
	}

	@Test(expected=ProductNotFoundException.class)
	public void verifyThrownProductNotFoundException() throws ProductNotFoundException {
		Map<String, Product> products = new HashMap<>();
		Assert.assertEquals("Banana", Product.findProductByName("Not a product", products));
	}
}
