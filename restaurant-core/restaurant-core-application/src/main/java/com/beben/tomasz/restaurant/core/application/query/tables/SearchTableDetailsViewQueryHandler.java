package com.beben.tomasz.restaurant.core.application.query.tables;

import com.beben.tomasz.cqrs.api.query.QueryHandler;
import com.beben.tomasz.restaurant.core.api.query.tables.SearchTableDetailsViewQuery;
import com.beben.tomasz.restaurant.core.api.view.RestaurantTableView;
import com.beben.tomasz.restaurant.core.application.converters.ToRestaurantTableViewConverter;
import com.beben.tomasz.restaurant.core.domain.RestaurantTable;
import com.beben.tomasz.restaurant.core.domain.RestaurantTableRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SearchTableDetailsViewQueryHandler implements QueryHandler<SearchTableDetailsViewQuery, RestaurantTableView> {

    private RestaurantTableRepository restaurantTableRepository;

    private ToRestaurantTableViewConverter toRestaurantTableViewConverter;

    @Override
    public RestaurantTableView handle(SearchTableDetailsViewQuery searchTableDetailsViewQuery) {
        RestaurantTable restaurantTable = restaurantTableRepository.find(searchTableDetailsViewQuery.getId());
        return toRestaurantTableViewConverter.convert(restaurantTable);
    }
}
