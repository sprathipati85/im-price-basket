package com.pricebasket.bjss.service.calculate;

import com.pricebasket.bjss.model.DiscountRule;
import com.pricebasket.bjss.model.PriceBasketResponse;
import com.pricebasket.bjss.model.Product;
import com.pricebasket.bjss.model.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface IPriceCalculationService {
    PriceBasketResponse calculateTotalPrice(Map<String, Product> products, ShoppingCart cart, List<DiscountRule> discountRules);

    List<DiscountRule> generateApplicableDiscountRules(ShoppingCart cart, Map<String, Product> products);

}
