package com.beben.tomasz.restaurant.user.api.view;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class RestaurantUserView {

    private String restaurantReference;

    private List<RoleTypeView> roleTypeViews;
}
