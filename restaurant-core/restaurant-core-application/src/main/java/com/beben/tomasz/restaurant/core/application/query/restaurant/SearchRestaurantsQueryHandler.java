package com.beben.tomasz.restaurant.core.application.query.restaurant;

import com.beben.tomasz.restaurant.core.api.query.restaurant.SearchRestaurantsQuery;
import com.beben.tomasz.restaurant.core.api.view.RestaurantView;
import com.beben.tomasz.restaurant.core.application.converters.ToRestaurantViewConverter;
import com.beben.tomasz.restaurant.core.domain.Restaurant;
import com.beben.tomasz.restaurant.core.domain.RestaurantRepository;
import com.beben.tomasz.cqrs.api.query.QueryHandler;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class SearchRestaurantsQueryHandler implements QueryHandler<SearchRestaurantsQuery, List<RestaurantView>> {

    private RestaurantRepository restaurantRepository;

    private ToRestaurantViewConverter toRestaurantViewConverter;

    @Override
    public List<RestaurantView> handle(SearchRestaurantsQuery searchRestaurantsQuery) {
        List<Restaurant> restaurants = restaurantRepository.findAll(searchRestaurantsQuery.getPage(), searchRestaurantsQuery.getSize());

        return toRestaurantViewConverter.convert(restaurants);
    }
}
