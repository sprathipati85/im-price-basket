package com.pricebasket.bjss.model;

import org.junit.Assert;
import org.junit.Test;

public class ProductItemTest {

	@Test
	public void verifyTheValuesOfProductItem() {
		ProductItem productItem = new ProductItem("Apple", "2");
		Assert.assertEquals("Apple", productItem.getProductName());
		Assert.assertEquals("2", productItem.getProductQuantity());
	}
}
