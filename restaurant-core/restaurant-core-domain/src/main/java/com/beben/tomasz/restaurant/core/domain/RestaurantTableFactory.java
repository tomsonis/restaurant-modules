package com.beben.tomasz.restaurant.core.domain;

public interface RestaurantTableFactory {

    RestaurantTable createTable(
            String name,
            String position,
            RestaurantId restaurantId,
            int capacity
    ) throws RestaurantNotExistException;
}
