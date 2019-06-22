package com.beben.tomasz.restaurant.products.application.model;

import com.beben.tomasz.restaurant.products.domain.Allergen;

public class TestAllergen implements Allergen {

    private static final String TEST_ID = "TEST_ID";
    public static final String TEST_NAME = "TEST_NAME";
    private static final String TEST_DESC = "TEST_DESC";
    private static final String TEST_REST_REF = "TEST_REST_REF";

    @Override
    public String getId() {
        return TEST_ID;
    }

    @Override
    public String getName() {
        return TEST_NAME;
    }

    @Override
    public String getDescription() {
        return TEST_DESC;
    }

    @Override
    public String getRestaurantReference() {
        return TEST_REST_REF;
    }
}
