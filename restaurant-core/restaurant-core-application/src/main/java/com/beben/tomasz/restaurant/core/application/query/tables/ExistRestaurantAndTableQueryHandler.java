package com.beben.tomasz.restaurant.core.application.query.tables;

import com.beben.tomasz.cqrs.api.query.QueryHandler;
import com.beben.tomasz.restaurant.core.api.query.ExistRestaurantAndTableQuery;
import com.beben.tomasz.restaurant.core.domain.RestaurantRepository;
import com.beben.tomasz.restaurant.core.domain.RestaurantTableRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExistRestaurantAndTableQueryHandler implements QueryHandler<ExistRestaurantAndTableQuery, Boolean> {

    private RestaurantRepository restaurantRepository;

    private RestaurantTableRepository restaurantTableRepository;

    @Override
    public Boolean handle(ExistRestaurantAndTableQuery existRestaurantAndTableQuery) {
        boolean exists = restaurantRepository.exists(existRestaurantAndTableQuery.getRestaurantReference());
        boolean exists1 = restaurantTableRepository.exists(existRestaurantAndTableQuery.getTableReference());

        return exists && exists1;
    }
}
