package com.beben.tomasz.restaurant.core.application.factory;

import com.beben.tomasz.restaurant.core.domain.RestaurantTable;

public class TestRestaurantTableFactory implements RestaurantTable {

    public static final String TEST_ID = "TEST_ID";
    public static final String TEST_NAME = "TEST_NAME";
    public static final String TEST_POSITION = "TEST_POSITION";
    public static final String TEST_RESTAURANT_REFERENCE = "TEST_RESTAURANT_REFERENCE";
    public static final int TEST_CAPACITY = 1;

    public static RestaurantTable createTable() {
        return new TestRestaurantTableFactory();
    }

    @Override
    public String getId() {
        return TEST_ID;
    }

    @Override
    public String getName() {
        return TEST_NAME;
    }

    @Override
    public String getPosition() {
        return TEST_POSITION;
    }

    @Override
    public String getRestaurantReference() {
        return TEST_RESTAURANT_REFERENCE;
    }

    @Override
    public int getCapacity() {
        return TEST_CAPACITY;
    }
}
