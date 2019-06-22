package com.beben.tomasz.restaurant.core.infrastructure.spring.persistence;

import com.beben.tomasz.restaurant.core.domain.Address;
import com.beben.tomasz.restaurant.core.domain.Restaurant;
import com.beben.tomasz.restaurant.core.domain.RestaurantFactory;
import com.beben.tomasz.restaurant.core.domain.RestaurantRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DefaultRestaurantFactory implements RestaurantFactory {

    private RestaurantRepository restaurantRepository;

    @Override
    public Restaurant createRestaurant(
            String name,
            String description,
            Address address,
            String photoUrl
    ) {
        return RestaurantEntity.of(
                restaurantRepository.generateId().getId(),
                name,
                description,
                RestaurantAddress.of(
                        address.getStreet(),
                        address.getPostalCode(),
                        address.getCity(),
                        address.getCountry()
                ),
                photoUrl
        );
    }
}
