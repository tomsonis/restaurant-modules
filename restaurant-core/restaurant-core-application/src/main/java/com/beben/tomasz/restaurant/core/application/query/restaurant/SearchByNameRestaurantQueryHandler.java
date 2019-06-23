package com.beben.tomasz.restaurant.core.application.query.restaurant;

import com.beben.tomasz.cqrs.api.query.QueryHandler;
import com.beben.tomasz.restaurant.core.api.query.restaurant.SearchByRestaurantNameQuery;
import com.beben.tomasz.restaurant.core.api.view.RestaurantView;
import com.beben.tomasz.restaurant.core.application.converters.ToRestaurantViewConverter;
import com.beben.tomasz.restaurant.core.domain.Restaurant;
import com.beben.tomasz.restaurant.core.domain.RestaurantRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class SearchByNameRestaurantQueryHandler implements QueryHandler<SearchByRestaurantNameQuery, List<RestaurantView>> {

    private RestaurantRepository restaurantRepository;

    private ToRestaurantViewConverter toRestaurantViewConverter;

    @Override
    public List<RestaurantView> handle(SearchByRestaurantNameQuery searchByRestaurantNameQuery) {
        List<Restaurant> restaurants = restaurantRepository.findByName(searchByRestaurantNameQuery.getName());
        return toRestaurantViewConverter.convert(restaurants);
    }
}
