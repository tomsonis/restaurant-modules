package com.beben.tomasz.restaurant.core.infrastructure.spring.persistence;

import com.beben.tomasz.restaurant.core.domain.Restaurant;
import com.beben.tomasz.restaurant.core.domain.RestaurantId;
import com.beben.tomasz.restaurant.core.domain.RestaurantNotExistException;
import com.beben.tomasz.restaurant.core.domain.RestaurantRepository;
import com.beben.tomasz.restaurant.core.domain.RestaurantTable;
import com.beben.tomasz.restaurant.core.domain.RestaurantTableFactory;
import com.beben.tomasz.restaurant.core.domain.RestaurantTableRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DefaultRestaurantTableFactory implements RestaurantTableFactory {

    private RestaurantTableRepository restaurantTableRepository;

    private RestaurantRepository restaurantRepository;

    @Override
    public RestaurantTable createTable(String name, String position, RestaurantId restaurantId, int capacity) throws RestaurantNotExistException {
        Restaurant restaurant = restaurantEntity(restaurantId);

        return RestaurantTableEntity.of(
                restaurantTableRepository.generateId().getId(),
                name,
                position,
                restaurant.getRestaurantId().getId(),
                capacity
        );
    }

    private Restaurant restaurantEntity(RestaurantId restaurantId) throws RestaurantNotExistException {
        return restaurantRepository.findById(restaurantId);
    }
}
