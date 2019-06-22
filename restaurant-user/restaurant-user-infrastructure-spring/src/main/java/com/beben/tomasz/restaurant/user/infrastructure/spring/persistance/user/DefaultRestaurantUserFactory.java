package com.beben.tomasz.restaurant.user.infrastructure.spring.persistance.user;

import com.beben.tomasz.restaurant.user.domain.RestaurantRoleType;
import com.beben.tomasz.restaurant.user.domain.RestaurantUser;
import com.beben.tomasz.restaurant.user.domain.RestaurantUserFactory;
import com.beben.tomasz.restaurant.user.domain.RestaurantUserRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

@AllArgsConstructor
public class DefaultRestaurantUserFactory implements RestaurantUserFactory {

    private RestaurantUserRepository restaurantUserRepository;

    @Override
    public RestaurantUser createUser(String restaurantReference, Collection<RestaurantRoleType> restaurantRoleTypes) {
        if (StringUtils.isBlank(restaurantReference)) {
            return null;
        }

        return RestaurantUserEntity.of(
                restaurantUserRepository.generateId().getId(),
                restaurantReference,
                restaurantRoleTypes
        );
    }
}
