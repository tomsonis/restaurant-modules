package com.beben.tomasz.restaurant.core.api.query.restaurant;

import com.beben.tomasz.cqrs.api.query.Query;
import com.beben.tomasz.restaurant.core.api.view.RestaurantView;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class SearchRestaurantsQuery implements Query<List<RestaurantView>> {

    @NotEmpty
    private int page;

    @NotEmpty
    private int size;
}
