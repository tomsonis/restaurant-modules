package com.beben.tomasz.restaurant.core.domain;

public interface Restaurant {

    RestaurantId getRestaurantId();

    String getName();

    String getDescription();

    Address getAddress();

    String getPhotoUrl();
}
