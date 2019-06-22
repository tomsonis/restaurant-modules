package com.beben.tomasz.restaurant.core.infrastructure.spring.persistence;

import com.beben.tomasz.restaurant.core.domain.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DefaultRestaurantTableFactory implements RestaurantTableFactory {

    private RestaurantTableRepository restaurantTableRepository;

    private RestaurantRepository restaurantRepository;

    @Override
    public RestaurantTable createTable(String name, String position, String restaurantId, int capacity) throws RestaurantNotExistException {
        Restaurant restaurant = restaurantEntity(restaurantId);

        return RestaurantTableEntity.of(
                restaurantTableRepository.generateId().getId(),
                name,
                position,
                restaurant.getId(),
                capacity
        );
    }

    private Restaurant restaurantEntity(String restaurantId) throws RestaurantNotExistException {
        return restaurantRepository.findById(restaurantId);
    }
}
