package com.pricebasket.bjss.service.load;

import com.pricebasket.bjss.model.Offer;
import com.pricebasket.bjss.model.Product;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
* Service to load all the Products and Offers
* Creates Map of Products and available Offers
*/
@Service
public class LoadDataService implements ILoadDataService{

    @Override
    public Map<String, Product> loadDataFromCsvFiles() throws IOException{
        Stream<String> products = Files.lines(Paths.get("src/main/resources/products.csv"));
        Stream<String> offers = Files.lines(Paths.get("src/main/resources/offers.csv"));
        Map<String, List<Offer>> offersGroupedByProduct = extractListOfOffers(offers);
        Map<String, Product> productMap = extractListOfProducts(products, offersGroupedByProduct);
        return productMap;
    }


    private Map<String, Product> extractListOfProducts(Stream<String> products, Map<String, List<Offer>> offersGroupedByProduct) {
        Map<String, Product> productMap = new HashMap<>();
        List<Product> listOfProducts = products.skip(1).map(line -> {
            String[] item = line.split(",");
            Product product = new Product(item[0], item[1]);
            if(offersGroupedByProduct.containsKey(product.getProductName())) {
                product.setDiscountOffers(offersGroupedByProduct.get(product.getProductName()));
            }
            return product;
        }).collect(Collectors.toList());
        productMap = listOfProducts.stream()
                .collect(Collectors.toMap(Product::getProductName, product -> product));
        productMap.values().stream().forEach(System.out::println);

        return productMap;
    }

    private Map<String, List<Offer>> extractListOfOffers(Stream<String> offers) {
        Map<String, List<Offer>> offersGroupedByProduct = new HashMap<>();
        List<Offer> listOffers = offers.skip(1).map(line -> {
            String[] item = line.split(",");
            return new Offer(item[0], item[1],item[2], item[3], item[4], item[5]);
        }).collect(Collectors.toList());

        offersGroupedByProduct =
                listOffers.stream()
                        .collect(Collectors.toMap(Offer::getProductName, Arrays::asList));

        offersGroupedByProduct.values().stream().forEach(System.out::println);
        return offersGroupedByProduct;
    }
}
