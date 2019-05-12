package com.pricebasket.bjss;

import com.pricebasket.bjss.model.*;
import com.pricebasket.bjss.service.IPriceBasketService;
import com.pricebasket.bjss.service.calculate.IPriceCalculationService;
import com.pricebasket.bjss.service.load.LoadDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.*;

@SpringBootApplication
@ComponentScan(basePackages = "com.pricebasket.bjss")
public class Application implements CommandLineRunner {
	private static Logger LOGGER = LoggerFactory.getLogger(Application.class);

	@Autowired
	IPriceBasketService priceBasketService;

	@Autowired
	LoadDataService loadDataService;

	@Autowired
	IPriceCalculationService priceCalculationService;

	public static void main(String[] args)  {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("EXECUTING : command line runner");
		ShoppingCart cart = new ShoppingCart();
		Map<String, Product> products = loadDataService.loadDataFromCsvFiles();
		if(priceBasketService.isInputValid(args, cart, products)) {
			priceBasketService.normalizeCart(cart);
			List<DiscountRule> discountRules = priceCalculationService.generateApplicableDiscountRules(cart, products);
			PriceBasketResponse response = priceCalculationService.calculateTotalPrice(products, cart, discountRules);
			flushResponse(response);
		}
		else{
			System.out.println("Please insert valid input");
			System.out.println("java -jar im-price-basket-1.0-SNAPSHOT.jar Apples Bread Soup Milk");
		}
	}

	private void flushResponse(PriceBasketResponse response) {
		System.out.println("SubTotal : "+ response.getTotalBeforeDiscount());
		System.out.println(response.getDiscountsApplied());
		System.out.println("Total : "+ response.getTotalAfterDiscount());
	}
}
