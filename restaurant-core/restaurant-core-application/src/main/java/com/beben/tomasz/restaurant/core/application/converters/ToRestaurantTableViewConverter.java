package com.beben.tomasz.restaurant.core.application.converters;

import com.beben.tomasz.restaurant.commons.Converter;
import com.beben.tomasz.restaurant.core.api.view.RestaurantTableView;
import com.beben.tomasz.restaurant.core.domain.RestaurantTable;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ToRestaurantTableViewConverter implements Converter<RestaurantTable, RestaurantTableView> {

    @Override
    public RestaurantTableView convert(RestaurantTable restaurantTable) {
        return RestaurantTableView.of(
                restaurantTable.getId(),
                restaurantTable.getName(),
                restaurantTable.getPosition(),
                restaurantTable.getRestaurantReference(),
                restaurantTable.getCapacity()
        );
    }
}
