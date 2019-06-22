package com.beben.tomasz.restaurant.core.domain;

public interface RestaurantFactory {

    Restaurant createRestaurant(
            String name,
            String description,
            Address address,
            String photoUrl
    );
}
