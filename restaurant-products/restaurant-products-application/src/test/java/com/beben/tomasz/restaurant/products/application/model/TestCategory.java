package com.beben.tomasz.restaurant.products.application.model;

import com.beben.tomasz.restaurant.products.domain.Category;

public class TestCategory implements Category {

    public static final String TEST_ID = "TEST_ID";
    private static final String TEST_NAME = "TEST_NAME";
    private static final int TEST_ORDER_ON_LIST = 0;
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
    public int getOrderOnList() {
        return TEST_ORDER_ON_LIST;
    }

    @Override
    public String getRestaurantReference() {
        return TEST_REST_REF;
    }
}
