package com.beben.tomasz.restaurant.core.application.factory;

import com.beben.tomasz.restaurant.core.domain.Address;
import com.beben.tomasz.restaurant.core.domain.Restaurant;
import com.beben.tomasz.restaurant.core.domain.RestaurantId;

public class TestRestaurantFactory implements Restaurant {

    public static final RestaurantId TEST_RESTAURANT_ID = RestaurantId.of("TEST_RESTAURANT_ID");
    public static final String TEST_STREET = "TEST_STREET";
    public static final String TEST_POSTAL_CODE = "TEST_POSTAL_CODE";
    public static final String TEST_CITY = "TEST_CITY";
    public static final String TEST_COUNTRY = "TEST_COUNTRY";
    public static final String TEST_NAME = "TEST_NAME";
    public static final String TEST_DESC = "TEST_DESC";
    public static final String TEST_PHOTO = "TEST_PHOTO";

    public static Restaurant createRestaurant() {
        return new TestRestaurantFactory();
    }

    @Override
    public RestaurantId getRestaurantId() {
        return TEST_RESTAURANT_ID;
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
    public Address getAddress() {
        return Address.of(
                TEST_STREET,
                TEST_POSTAL_CODE,
                TEST_CITY,
                TEST_COUNTRY
        );
    }

    @Override
    public String getPhotoUrl() {
        return TEST_PHOTO;
    }
}
