package com.beben.tomasz.restaurant.user.api.query;

import com.beben.tomasz.cqrs.api.query.Query;
import com.beben.tomasz.restaurant.user.api.view.RestaurantUserView;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "of")
public class RestaurantUserQuery implements Query<Option<RestaurantUserView>> {

}
