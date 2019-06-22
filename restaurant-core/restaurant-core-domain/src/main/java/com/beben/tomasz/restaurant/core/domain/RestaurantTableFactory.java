package com.beben.tomasz.restaurant.core.domain;

public interface RestaurantTableFactory {

    RestaurantTable createTable(String name, String position, String restaurantId, int capacity) throws RestaurantNotExistException;
}
