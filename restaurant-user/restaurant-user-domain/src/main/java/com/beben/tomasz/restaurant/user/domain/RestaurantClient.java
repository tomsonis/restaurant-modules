package com.beben.tomasz.restaurant.user.domain;

public interface RestaurantClient {

    ClientId getClientId();

    String getName();

    String getSurname();

    String getPhoneNumber();
}
