package com.beben.tomasz.restaurant.user.application.converters;

import com.beben.tomasz.restaurant.commons.Converter;
import com.beben.tomasz.restaurant.user.api.view.RestaurantUserView;
import com.beben.tomasz.restaurant.user.api.view.RoleTypeView;
import com.beben.tomasz.restaurant.user.domain.ApplicationUser;
import lombok.AllArgsConstructor;

import java.util.stream.Collectors;

@AllArgsConstructor
public class ToRestaurantUserViewConverter implements Converter<ApplicationUser, RestaurantUserView> {

    @Override
    public RestaurantUserView convert(ApplicationUser applicationUser) {

        return RestaurantUserView.of(
                applicationUser.getRestaurantUser().getRestaurantReference(),
                applicationUser.getRestaurantUser().getRestaurantRoleTypes()
                        .stream()
                        .map(restaurantRoleType -> RoleTypeView.valueOf(restaurantRoleType.name()))
                        .collect(Collectors.toList())
        );
    }
}
