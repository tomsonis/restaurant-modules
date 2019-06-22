package com.beben.tomasz.restaurant.user.domain;

import java.util.Collection;

public interface RestaurantUser {

    String getRestaurantReference();

    Collection<RestaurantRoleType> getRestaurantRoleTypes();
}
