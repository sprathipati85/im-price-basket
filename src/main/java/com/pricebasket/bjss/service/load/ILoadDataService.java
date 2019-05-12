package com.pricebasket.bjss.service.load;

import com.pricebasket.bjss.model.Product;

import java.io.IOException;
import java.util.Map;

public interface ILoadDataService {

    Map<String, Product> loadDataFromCsvFiles() throws IOException;
}
