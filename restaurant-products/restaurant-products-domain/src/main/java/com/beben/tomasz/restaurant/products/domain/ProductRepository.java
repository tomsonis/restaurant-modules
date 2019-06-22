package com.beben.tomasz.restaurant.products.domain;

public interface ProductRepository {

    String generateId();

    void save(Product product);
}
