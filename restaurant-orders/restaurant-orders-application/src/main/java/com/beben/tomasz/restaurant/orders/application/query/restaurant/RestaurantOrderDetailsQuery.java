package com.beben.tomasz.restaurant.orders.application.query.restaurant;

import com.beben.tomasz.cqrs.api.query.Query;
import com.beben.tomasz.restaurant.orders.api.OrderDetailsView;
import com.beben.tomasz.restaurant.orders.domain.order.OrderId;
import io.vavr.control.Option;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class RestaurantOrderDetailsQuery implements Query<Option<OrderDetailsView>> {

    @NotNull
    private OrderId orderId;
}
