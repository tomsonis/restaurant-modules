package com.beben.tomasz.restaurant.user.domain;

import java.util.Collection;

public interface RestaurantUserFactory {

    RestaurantUser createUser(
            String restaurantReference,
            Collection<RestaurantRoleType> restaurantRoleTypes
    );
}
