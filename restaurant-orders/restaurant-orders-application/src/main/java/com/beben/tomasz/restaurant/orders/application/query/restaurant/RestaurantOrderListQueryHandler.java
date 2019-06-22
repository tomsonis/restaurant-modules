package com.beben.tomasz.restaurant.orders.application.query.restaurant;

import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import com.beben.tomasz.cqrs.api.query.QueryHandler;
import com.beben.tomasz.restaurant.orders.api.OrderDetailsView;
import com.beben.tomasz.restaurant.orders.api.query.restaurant.RestaurantOrderListQuery;
import com.beben.tomasz.restaurant.orders.application.converter.ToOrderDetailsViewConverter;
import com.beben.tomasz.restaurant.orders.application.query.OrderReadRepository;
import com.beben.tomasz.restaurant.orders.domain.order.Order;
import com.beben.tomasz.restaurant.user.api.query.RestaurantUserQuery;
import com.beben.tomasz.restaurant.user.api.view.RestaurantUserView;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class RestaurantOrderListQueryHandler implements QueryHandler<RestaurantOrderListQuery, List<OrderDetailsView>> {

    private OrderReadRepository orderReadRepository;

    private ToOrderDetailsViewConverter toOrderDetailsViewConverter;

    private QueryExecutor queryExecutor;

    @Override
    public List<OrderDetailsView> handle(RestaurantOrderListQuery restaurantOrderListQuery) throws Exception {

        RestaurantUserQuery restaurantUserQuery = RestaurantUserQuery.of();
        RestaurantUserView restaurantUserView = queryExecutor.execute(restaurantUserQuery).get();//TODO: to refactor

        List<Order> restaurantOrders = orderReadRepository.findRestaurantOrders(
                restaurantUserView.getRestaurantReference(),
                restaurantOrderListQuery.getPage(),
                restaurantOrderListQuery.getSize()
        );

        return toOrderDetailsViewConverter.convert(restaurantOrders);
    }
}
