package com.beben.tomasz.restaurant.core.application;

import com.beben.tomasz.restaurant.core.api.view.RestaurantTableView;
import com.beben.tomasz.restaurant.core.api.view.RestaurantView;
import com.beben.tomasz.restaurant.core.domain.Restaurant;
import com.beben.tomasz.restaurant.core.domain.RestaurantTable;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertUtils {

    public static void assertRestaurantView(RestaurantView restaurantView, Restaurant restaurant) {
        assertThat(restaurantView).isNotNull();
        assertThat(restaurantView.getId()).isEqualTo(restaurant.getRestaurantId().getId());
        assertThat(restaurantView.getName()).isEqualTo(restaurant.getName());
        assertThat(restaurantView.getPhotoUrl()).isEqualTo(restaurant.getPhotoUrl());

        assertThat(restaurantView.getAddress()).isNotNull();
        assertThat(restaurantView.getAddress().getStreet()).isEqualTo(restaurant.getAddress().getStreet());
        assertThat(restaurantView.getAddress().getCity()).isEqualTo(restaurant.getAddress().getCity());
        assertThat(restaurantView.getAddress().getPostalCode()).isEqualTo(restaurant.getAddress().getPostalCode());
        assertThat(restaurantView.getAddress().getCountry()).isEqualTo(restaurant.getAddress().getCountry());
    }

    public static void assertRestaurantTableView(RestaurantTableView restaurantTableView, RestaurantTable restaurantTable) {
        assertThat(restaurantTableView).isNotNull();
        assertThat(restaurantTableView.getId()).isEqualTo(restaurantTable.getTableId().getId());
        assertThat(restaurantTableView.getName()).isEqualTo(restaurantTable.getName());
        assertThat(restaurantTableView.getRestaurantReference()).isEqualTo(restaurantTable.getRestaurantReference().getId());
        assertThat(restaurantTableView.getPosition()).isEqualTo(restaurantTable.getPosition());
        assertThat(restaurantTableView.getCapacity()).isEqualTo(restaurantTable.getCapacity());

    }
}
