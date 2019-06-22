package com.beben.tomasz.restaurant.orders.api.query.user;

import com.beben.tomasz.cqrs.api.query.Query;
import com.beben.tomasz.restaurant.orders.api.OrderDetailsView;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@AllArgsConstructor(staticName = "of")
public class OrderListQuery implements Query<List<OrderDetailsView>> {

    @NotEmpty
    private int page;

    @NotEmpty
    private int size;
}
