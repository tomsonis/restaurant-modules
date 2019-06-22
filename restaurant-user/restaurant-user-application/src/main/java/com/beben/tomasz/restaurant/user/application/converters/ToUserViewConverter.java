package com.beben.tomasz.restaurant.user.application.converters;

import com.beben.tomasz.restaurant.commons.Converter;
import com.beben.tomasz.restaurant.user.api.view.RoleTypeView;
import com.beben.tomasz.restaurant.user.api.view.UserDetailsView;
import com.beben.tomasz.restaurant.user.domain.ApplicationUser;
import com.beben.tomasz.restaurant.user.domain.RestaurantClient;
import com.beben.tomasz.restaurant.user.domain.RestaurantUser;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ToUserViewConverter implements Converter<ApplicationUser, UserDetailsView> {

    @Override
    public UserDetailsView convert(ApplicationUser applicationUser) {
        UserDetailsView.Builder builder = UserDetailsView.builder();

        builder.email(applicationUser.getEmail());

        RestaurantClient restaurantClient = applicationUser.getRestaurantClient();
        if (Objects.nonNull(restaurantClient)) {
            builder.name(restaurantClient.getName())
                    .surname(restaurantClient.getSurname())
                    .phoneNumber(restaurantClient.getPhoneNumber());
        }

        RestaurantUser restaurantUser = applicationUser.getRestaurantUser();
        if (Objects.nonNull(restaurantUser)) {
            builder.restaurantReference(restaurantUser.getRestaurantReference());

            List<RoleTypeView> roleTypeViews = restaurantUser.getRestaurantRoleTypes()
                    .stream()
                    .map(restaurantRoleType -> RoleTypeView.valueOf(restaurantRoleType.name()))
                    .collect(Collectors.toList());

            builder.roleTypes(roleTypeViews);
        }

        return builder.build();
    }
}
