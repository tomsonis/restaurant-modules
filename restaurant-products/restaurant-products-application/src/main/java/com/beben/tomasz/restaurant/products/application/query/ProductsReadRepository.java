package com.beben.tomasz.restaurant.products.application.query;

import com.beben.tomasz.restaurant.products.domain.Category;
import com.beben.tomasz.restaurant.products.domain.Product;

import java.util.List;
import java.util.Map;

public interface ProductsReadRepository {

    List<Product> findAllByCategory(String categoryId, String restaurantReference);

    Map<Category, List<Product>> findProductsGroupedByCategory(String restaurantId);

    List<Product> findByIds(List<String> productIds);
}
