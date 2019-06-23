package com.beben.tomasz.restaurant.core.api.query.tables;

import com.beben.tomasz.cqrs.api.query.Query;
import com.beben.tomasz.restaurant.core.api.view.RestaurantTableView;
import com.beben.tomasz.restaurant.core.domain.TableId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class SearchTableDetailsViewQuery implements Query<RestaurantTableView> {

    private TableId id;
}
