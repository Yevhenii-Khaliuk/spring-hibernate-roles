package com.khaliuk.service;

import com.khaliuk.model.Product;

public interface ProductService {

    Product reducePriceByPercents(Product product, int percents);

}
