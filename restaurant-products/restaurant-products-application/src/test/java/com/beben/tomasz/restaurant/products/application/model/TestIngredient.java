package com.beben.tomasz.restaurant.products.application.model;

import com.beben.tomasz.restaurant.products.domain.Ingredient;
import com.beben.tomasz.restaurant.products.domain.Volume;

public class TestIngredient implements Ingredient {

    public static final String TEST_NAME = "TEST_NAME";
    private static final String TEST_ID = "TEST_ID";
    private static final String TEST_RESTAURANT_REFERENCE = "TEST_RESTAURANT_REFERENCE";

    @Override
    public String getId() {
        return TEST_ID;
    }

    @Override
    public String getName() {
        return TEST_NAME;
    }

    @Override
    public String getRestaurantReference() {
        return TEST_RESTAURANT_REFERENCE;
    }

    @Override
    public Volume getVolume() {
        return new TestVolume();
    }
}
