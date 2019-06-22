package com.beben.tomasz.restaurant.core.api.query.tables;

import com.beben.tomasz.restaurant.core.api.view.RestaurantTableView;
import com.beben.tomasz.cqrs.api.query.Query;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class SearchTableDetailsViewQuery implements Query<RestaurantTableView> {

    private String id;
}
