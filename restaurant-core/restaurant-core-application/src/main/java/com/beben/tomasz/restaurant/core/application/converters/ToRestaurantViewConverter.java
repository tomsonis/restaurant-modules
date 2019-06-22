package com.beben.tomasz.restaurant.core.application.converters;

import com.beben.tomasz.restaurant.commons.Converter;
import com.beben.tomasz.restaurant.commons.view.AddressView;
import com.beben.tomasz.restaurant.core.api.view.RestaurantView;
import com.beben.tomasz.restaurant.core.domain.Restaurant;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ToRestaurantViewConverter implements Converter<Restaurant, RestaurantView> {

    @Override
    public RestaurantView convert(Restaurant restaurant) {
        return RestaurantView.of(
                restaurant.getId(),
                restaurant.getName(),
                AddressView.of(
                        restaurant.getAddress().getStreet(),
                        restaurant.getAddress().getPostalCode(),
                        restaurant.getAddress().getCity(),
                        restaurant.getAddress().getCountry()
                ),
                restaurant.getPhotoUrl()
        );
    }
}
