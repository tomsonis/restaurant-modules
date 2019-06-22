package com.beben.tomasz.restaurant.user.application.query;

import com.beben.tomasz.restaurant.commons.ContextHolder;
import com.beben.tomasz.cqrs.api.query.QueryHandler;
import com.beben.tomasz.restaurant.user.api.query.RestaurantUserQuery;
import com.beben.tomasz.restaurant.user.api.view.RestaurantUserView;
import com.beben.tomasz.restaurant.user.application.converters.ToRestaurantUserViewConverter;
import com.beben.tomasz.restaurant.user.domain.ApplicationUser;
import com.beben.tomasz.restaurant.user.domain.UserRepository;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class RestaurantUserQueryHandler implements QueryHandler<RestaurantUserQuery, Option<RestaurantUserView>> {

    private UserRepository userRepository;

    private ContextHolder contextHolder;

    private ToRestaurantUserViewConverter toRestaurantUserViewConverter;

    @Override
    public Option<RestaurantUserView> handle(RestaurantUserQuery restaurantUserQuery) {
        return contextHolder.getContext().getUserId()
                .flatMap(userRepository::findByUserId)
                .map(toRestaurantUserViewConverter::convert);
    }
}
