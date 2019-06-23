package com.beben.tomasz.restaurant.core.api.query;

import com.beben.tomasz.cqrs.api.query.Query;
import com.beben.tomasz.restaurant.core.domain.RestaurantId;
import com.beben.tomasz.restaurant.core.domain.TableId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class ExistRestaurantAndTableQuery implements Query<Boolean> {

    private RestaurantId restaurantReference;

    private TableId tableReference;
}
