package com.beben.tomasz.restaurant.orders.application.query.user.details;

import com.beben.tomasz.cqrs.api.query.QueryHandler;
import com.beben.tomasz.restaurant.commons.ContextHolder;
import com.beben.tomasz.restaurant.orders.api.OrderDetailsView;
import com.beben.tomasz.restaurant.orders.application.converter.ToOrderDetailsViewConverter;
import com.beben.tomasz.restaurant.orders.application.query.OrderReadRepository;
import com.beben.tomasz.restaurant.orders.domain.order.OrderId;
import com.beben.tomasz.restaurant.orders.domain.order.UserId;
import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public class OrderDetailsQueryHandler implements QueryHandler<OrderDetailsQuery, OrderDetailsView> {

    private OrderReadRepository orderReadRepository;

    private ToOrderDetailsViewConverter toOrderDetailsViewConverter;

    private ContextHolder contextHolder;

    @Override
    public OrderDetailsView handle(OrderDetailsQuery orderDetailsQuery) {
        OrderId orderId = orderDetailsQuery.getOrderId();

        UserId userId = contextHolder.getContext().getUserId()
                .map(UserId::of)
                .getOrElse(UserId.empty());

        return orderReadRepository.orderDetailsView(
                        orderId
                )
                .filter(order -> Objects.nonNull(order.getUserReference()) && order.getUserReference().equals(userId))
                .map(toOrderDetailsViewConverter::convert)
                .getOrElseThrow(() -> new IllegalArgumentException("Zamówienie nie istnieje."));
    }
}
