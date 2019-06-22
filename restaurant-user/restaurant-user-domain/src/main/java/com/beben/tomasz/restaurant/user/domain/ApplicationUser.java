package com.beben.tomasz.restaurant.user.domain;

public interface ApplicationUser {

    UserId getUserId();

    String getUsername();

    String getPassword();

    String getEmail();
    
    RestaurantClient getRestaurantClient();

    String getToken();

    RestaurantUser getRestaurantUser();
}
