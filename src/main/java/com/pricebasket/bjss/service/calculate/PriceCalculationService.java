package com.pricebasket.bjss.service.calculate;

import com.pricebasket.bjss.model.*;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
* Service to calculate the price of items in Basket
* Generates applicable discounts for the items in the cart
* Process and calculates the price
*/

@Service
public class PriceCalculationService implements IPriceCalculationService {
    private static Logger LOGGER = LoggerFactory.getLogger(PriceCalculationService.class);

    @Override
    public PriceBasketResponse calculateTotalPrice(Map<String, Product> productMap, ShoppingCart cart, List<DiscountRule> discountRules) {
        PriceBasketResponse priceBasketResponse = new PriceBasketResponse();
        StringBuffer response = new StringBuffer();
        BigDecimal productPrice = BigDecimal.ZERO;
        BigDecimal discountPrice = BigDecimal.ZERO;

        for(ProductItem item : cart.getItems() ){
            String productName = item.getProductName();
            if (productMap.containsKey(productName)) {
                Product product = productMap.get(productName);
                productPrice = productPrice.add(new BigDecimal(product.getProductPrice()).multiply(new BigDecimal(item.getProductQuantity())));
                discountPrice = discountPrice.add(applyDiscountRules(response, product, discountRules));
            }
        }
        generatePriceBasketResponse(priceBasketResponse, response, productPrice, discountPrice);
        return priceBasketResponse;
    }

    @Override
    public List<DiscountRule> generateApplicableDiscountRules(ShoppingCart cart, Map<String, Product> products) {
        List<DiscountRule> applicableDiscountRules = new ArrayList<>();
        for(ProductItem item : cart.getItems() ) {
            DiscountRule discountRule = new DiscountRule();
            if (products.containsKey(item.getProductName())) {
                Product product = products.get(item.getProductName());
                if (CollectionUtils.isNotEmpty(product.getDiscountOffers())) {
                    for (Offer offer : product.getDiscountOffers()) {
                        if (item.getProductName().equalsIgnoreCase(offer.getProductName())
                                && item.getProductQuantity().equals(offer.getProductQuantity())) {
                            discountRule.setDiscountPercent(offer.getDiscountProductPrice());
                            discountRule.setProductname(offer.getDiscountProductName());
                            applicableDiscountRules.add(discountRule);
                        }
                    }
                }
            }
        }
        applicableDiscountRules.stream().forEach(System.out::println);
        return applicableDiscountRules;
    }

    private BigDecimal applyDiscountRules(StringBuffer response, Product product, List<DiscountRule> discountRules) {
        BigDecimal discountPrice = BigDecimal.ZERO;
        for(DiscountRule rule : discountRules){
            if(rule.getProductname().equalsIgnoreCase(product.getProductName())) {
                discountPrice = discountPrice.add(calculateDiscountPrice(new BigDecimal(product.getProductPrice()), rule.getDiscountPercent()));
                response.append(product.getProductName())
                        .append(" - ")
                        .append(rule.getDiscountPercent())
                        .append("% off: ")
                        .append(discountPrice)
                        .append(" ");
            }
        }
        return discountPrice;
    }

    private void generatePriceBasketResponse(PriceBasketResponse priceBasketResponse, StringBuffer response, BigDecimal productPrice, BigDecimal discountPrice) {
        priceBasketResponse.setTotalBeforeDiscount(productPrice);
        priceBasketResponse.setDiscountedPrice(discountPrice);
        priceBasketResponse.setTotalAfterDiscount(priceBasketResponse.getTotalBeforeDiscount()
                .subtract(priceBasketResponse.getDiscountedPrice()));
        if (priceBasketResponse.getDiscountedPrice().equals(BigDecimal.ZERO)){
            priceBasketResponse.setDiscountsApplied("(No offers available)");
        }else{
            priceBasketResponse.setDiscountsApplied(response.toString());

        }
    }

    private BigDecimal calculateDiscountPrice(BigDecimal price, String discountProductPrice) {
        return (price.multiply(new BigDecimal(discountProductPrice))).divide(BigDecimal.valueOf(100));
    }
}
