package com.beben.tomasz.restaurant.core.domain;

import java.util.List;

public interface RestaurantRepository {

    String save(Restaurant restaurant);

    Restaurant findById(String id) throws RestaurantNotExistException;

    RestaurantId generateId();

    List<Restaurant> findByName(String name);

    List<Restaurant> findAll(int page, int size);

    boolean exists(String restaurantId);
}
