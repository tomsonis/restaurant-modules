package com.beben.tomasz.restaurant.user.domain;

public interface UserFactory {

    ApplicationUser createUser(
            String username,
            String password,
            String email,
            RestaurantClient restaurantClient,
            RestaurantUser restaurantUser
    );
}
