package com.beben.tomasz.restaurant.core.api.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(staticName = "newInstance")
@AllArgsConstructor(staticName = "of")
public class RestaurantTableView {

    private String id;

    private String name;

    private String position;

    private String restaurantReference;

    private int capacity;
}
