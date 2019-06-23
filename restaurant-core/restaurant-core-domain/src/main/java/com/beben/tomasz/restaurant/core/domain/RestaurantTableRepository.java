package com.beben.tomasz.restaurant.core.domain;

import java.util.List;

public interface RestaurantTableRepository {

    TableId generateId();

    TableId save(RestaurantTable restaurantTable);

    List<RestaurantTable> findByRestaurantAndDistinctCapacity(RestaurantId restaurantId);

    RestaurantTable find(TableId id);

    boolean exists(TableId tableId);
}
