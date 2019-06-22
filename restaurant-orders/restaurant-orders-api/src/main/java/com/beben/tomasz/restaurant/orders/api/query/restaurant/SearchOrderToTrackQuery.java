package com.beben.tomasz.restaurant.orders.api.query.restaurant;

import com.beben.tomasz.cqrs.api.query.Query;
import com.beben.tomasz.restaurant.orders.api.OrderDetailsView;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(staticName = "of")
public class SearchOrderToTrackQuery implements Query<List<OrderDetailsView>> {

    private int page;

    private int size;
}
