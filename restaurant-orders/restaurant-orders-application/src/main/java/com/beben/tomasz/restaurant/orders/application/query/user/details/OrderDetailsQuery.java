package com.beben.tomasz.restaurant.orders.application.query.user.details;

import com.beben.tomasz.cqrs.api.query.Query;
import com.beben.tomasz.restaurant.orders.api.OrderDetailsView;
import com.beben.tomasz.restaurant.orders.domain.order.OrderId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class OrderDetailsQuery implements Query<OrderDetailsView> {

    @NotNull
    private OrderId orderId;
}
