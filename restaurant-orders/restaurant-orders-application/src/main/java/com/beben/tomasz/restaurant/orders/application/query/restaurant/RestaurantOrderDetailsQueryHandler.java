package com.beben.tomasz.restaurant.orders.application.query.restaurant;

import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import com.beben.tomasz.cqrs.api.query.QueryHandler;
import com.beben.tomasz.restaurant.orders.api.OrderDetailsView;
import com.beben.tomasz.restaurant.orders.application.converter.ToOrderDetailsViewConverter;
import com.beben.tomasz.restaurant.orders.application.query.OrderReadRepository;
import com.beben.tomasz.restaurant.orders.domain.order.RestaurantId;
import com.beben.tomasz.restaurant.user.api.query.RestaurantUserQuery;
import com.beben.tomasz.restaurant.user.api.view.RestaurantUserView;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RestaurantOrderDetailsQueryHandler implements QueryHandler<RestaurantOrderDetailsQuery, Option<OrderDetailsView>> {

    private OrderReadRepository orderReadRepository;

    private ToOrderDetailsViewConverter toOrderDetailsViewConverter;

    private QueryExecutor queryExecutor;

    @Override
    public Option<OrderDetailsView> handle(RestaurantOrderDetailsQuery orderDetailsQuery) throws Exception {
        RestaurantUserView restaurantUserView = queryExecutor.execute(RestaurantUserQuery.of())
                        .getOrElseThrow(() -> new IllegalArgumentException("Error with restaurant information."));

        return Option.of(orderDetailsQuery.getOrderId())
                .flatMap(orderId -> orderReadRepository.restaurantOrderDetailsView(
                                orderId,
                                RestaurantId.of(restaurantUserView.getRestaurantReference())
                        )
                )
                .map(order -> toOrderDetailsViewConverter.convert(order));
    }
}
