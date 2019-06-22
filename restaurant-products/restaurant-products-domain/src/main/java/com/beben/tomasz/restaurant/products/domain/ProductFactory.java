package com.beben.tomasz.restaurant.products.domain;

import java.math.BigDecimal;
import java.util.Collection;

public interface ProductFactory {

    Product createProduct(
            String name,
            String description,
            Collection<String> ingredientIds,
            Collection<String> allergenIds,
            String volumeId,
            BigDecimal price,
            String photoUrl,
            boolean available,
            String categoryId,
            String restaurantReference
    );
}
