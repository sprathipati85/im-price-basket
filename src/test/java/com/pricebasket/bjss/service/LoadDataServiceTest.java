package com.pricebasket.bjss.service;

import com.pricebasket.bjss.model.Product;
import com.pricebasket.bjss.service.load.ILoadDataService;
import com.pricebasket.bjss.service.load.LoadDataService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LoadDataServiceTest {

	ILoadDataService loadDataService;

	@Before
	public void setup() {
		loadDataService = new LoadDataService();
	}
	
	@Test
	public void verifyTheDetailsLoadedFromCsvFiles() throws IOException {
		Map<String, Product> productMap = loadDataService.loadDataFromCsvFiles();
		assertNotNull(productMap);
		Product product = productMap.get("Apples");
		assertEquals(productMap.size(), 4);
		assertEquals(product.getProductName(), "Apples");
		assertEquals(product.getDiscountOffers().size(), 1);
	}
}
