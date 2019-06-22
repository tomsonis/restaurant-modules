package com.beben.tomasz.restaurant.products.domain;

import java.math.BigDecimal;
import java.util.Collection;

public interface Product {

    String getId();

    String getName();

    String getDescription();

    Collection<Ingredient> getIngredients();

    Collection<Allergen> getAllergens();

    BigDecimal getPrice();

    String getPhotoUrl();

    boolean isAvailable();

    Category getCategory();

    String getRestaurantReference();
}
