package com.beben.tomasz.restaurant.core.application.query.restaurant;

import com.beben.tomasz.restaurant.core.api.query.restaurant.SearchRestaurantDetailsQuery;
import com.beben.tomasz.restaurant.core.api.view.RestaurantView;
import com.beben.tomasz.restaurant.core.application.converters.ToRestaurantViewConverter;
import com.beben.tomasz.restaurant.core.domain.Restaurant;
import com.beben.tomasz.restaurant.core.domain.RestaurantRepository;
import com.beben.tomasz.cqrs.api.query.QueryHandler;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SearchRestaurantDetailsQueryHandler implements QueryHandler<SearchRestaurantDetailsQuery, RestaurantView> {

    private RestaurantRepository restaurantRepository;

    private ToRestaurantViewConverter toRestaurantViewConverter;

    @Override
    public RestaurantView handle(SearchRestaurantDetailsQuery searchRestaurantDetailsQuery) throws Exception {
        Restaurant restaurant = restaurantRepository.findById(searchRestaurantDetailsQuery.getRestaurantId());

        return toRestaurantViewConverter.convert(restaurant);
    }
}
