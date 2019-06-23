package com.beben.tomasz.restaurant.orders.application.query.user.list;

import com.beben.tomasz.cqrs.api.query.QueryHandler;
import com.beben.tomasz.restaurant.commons.ContextHolder;
import com.beben.tomasz.restaurant.orders.api.OrderDetailsView;
import com.beben.tomasz.restaurant.orders.api.query.user.OrderListQuery;
import com.beben.tomasz.restaurant.orders.application.converter.ToOrderDetailsViewConverter;
import com.beben.tomasz.restaurant.orders.application.query.OrderReadRepository;
import com.beben.tomasz.restaurant.orders.domain.order.Order;
import com.beben.tomasz.restaurant.orders.domain.order.UserId;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class OrderListQueryHandler implements QueryHandler<OrderListQuery, List<OrderDetailsView>> {

    private OrderReadRepository orderReadRepository;

    private ToOrderDetailsViewConverter toOrderDetailsViewConverter;

    private ContextHolder contextHolder;

    @Override
    public List<OrderDetailsView> handle(OrderListQuery orderListQuery) {

        List<Order> ordersByUser = orderReadRepository.findOrdersByUser(
                contextHolder.getContext().getUserId().map(UserId::of),
                orderListQuery.getPage(),
                orderListQuery.getSize()
        );

        return toOrderDetailsViewConverter.convert(ordersByUser);
    }
}
