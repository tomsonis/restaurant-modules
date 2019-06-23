package com.beben.tomasz.restaurant.core.api.query.restaurant;

import com.beben.tomasz.cqrs.api.query.Query;
import com.beben.tomasz.restaurant.core.api.view.RestaurantView;
import com.beben.tomasz.restaurant.core.domain.RestaurantId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class SearchRestaurantDetailsQuery implements Query<RestaurantView> {

    private RestaurantId restaurantId;
}
