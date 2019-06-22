package com.beben.tomasz.restaurant.products.application.model;

import com.beben.tomasz.restaurant.products.domain.Allergen;
import com.beben.tomasz.restaurant.products.domain.Category;
import com.beben.tomasz.restaurant.products.domain.Ingredient;
import com.beben.tomasz.restaurant.products.domain.Product;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;

public class TestProduct implements Product {

    private static final String TEST_ID = "TEST_ID";
    public static final String TEST_PROD_NAME = "TEST_PROD_NAME";
    public static final String TEST_PROD_DESC = "TEST_PROD_DESC";
    public static final String TEST_PHOTO_URL = "TEST_PHOTO_URL";
    public static final String TEST_REST_REF = "TEST_REST_REF";
    public static final boolean TEST_AVAILABLE = true;
    public static final BigDecimal TEST_PRICE = BigDecimal.valueOf(120.56);

    @Override
    public String getId() {
        return TEST_ID;
    }

    @Override
    public String getName() {
        return TEST_PROD_NAME;
    }

    @Override
    public String getDescription() {
        return TEST_PROD_DESC;
    }

    @Override
    public Collection<Ingredient> getIngredients() {
        return Collections.singletonList(
                new TestIngredient()
        );
    }

    @Override
    public Collection<Allergen> getAllergens() {
        return Collections.singletonList(
                new TestAllergen()
        );
    }

    @Override
    public BigDecimal getPrice() {
        return TEST_PRICE;
    }

    @Override
    public String getPhotoUrl() {
        return TEST_PHOTO_URL;
    }

    @Override
    public boolean isAvailable() {
        return TEST_AVAILABLE;
    }

    @Override
    public Category getCategory() {
        return new TestCategory();
    }

    @Override
    public String getRestaurantReference() {
        return TEST_REST_REF;
    }
}
