package com.beben.tomasz.restaurant.core.domain;

public interface RestaurantTable {

    TableId getTableId();

    String getName();

    String getPosition();

    RestaurantId getRestaurantReference();

    int getCapacity();
}
