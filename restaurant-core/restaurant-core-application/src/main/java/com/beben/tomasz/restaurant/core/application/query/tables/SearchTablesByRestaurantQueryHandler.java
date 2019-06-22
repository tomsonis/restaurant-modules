package com.beben.tomasz.restaurant.core.application.query.tables;

import com.beben.tomasz.restaurant.core.api.query.tables.SearchTablesByRestaurantQuery;
import com.beben.tomasz.restaurant.core.api.view.RestaurantTableView;
import com.beben.tomasz.restaurant.core.application.converters.ToRestaurantTableViewConverter;
import com.beben.tomasz.restaurant.core.domain.RestaurantTable;
import com.beben.tomasz.restaurant.core.domain.RestaurantTableRepository;
import com.beben.tomasz.cqrs.api.query.QueryHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public class SearchTablesByRestaurantQueryHandler implements QueryHandler<SearchTablesByRestaurantQuery, List<RestaurantTableView>> {

    private RestaurantTableRepository restaurantTableRepository;

    private ToRestaurantTableViewConverter toRestaurantTableViewConverter;

    @Override
    public List<RestaurantTableView> handle(SearchTablesByRestaurantQuery searchTablesByRestaurantQuery) {
        List<RestaurantTable> restaurantTables = restaurantTableRepository.findByRestaurantAndDistinctCapacity(
                searchTablesByRestaurantQuery.getRestaurantId()
        );

        return toRestaurantTableViewConverter.convert(restaurantTables);
    }
}
