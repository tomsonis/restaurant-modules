package com.beben.tomasz.restaurant.core.domain;

import java.util.List;

public interface RestaurantTableRepository {

    TableId generateId();

    String save(RestaurantTable restaurantTable);

    List<RestaurantTable> findByRestaurantAndDistinctCapacity(String restaurantReference);

    RestaurantTable find(String id);

    boolean exists(String tableId);
}
