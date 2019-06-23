package com.beben.tomasz.restaurant.core.api.query.tables;

import com.beben.tomasz.cqrs.api.query.Query;
import com.beben.tomasz.restaurant.core.api.view.RestaurantTableView;
import com.beben.tomasz.restaurant.core.domain.RestaurantId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class SearchTablesByRestaurantQuery implements Query<List<RestaurantTableView>> {

    private RestaurantId restaurantId;
}
