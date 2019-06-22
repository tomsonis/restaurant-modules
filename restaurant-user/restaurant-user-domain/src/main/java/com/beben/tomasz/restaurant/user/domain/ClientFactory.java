package com.beben.tomasz.restaurant.user.domain;

public interface ClientFactory {

    RestaurantClient createClient(
            String name,
            String surname,
            String phoneNumber
    );
}
