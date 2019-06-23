package com.beben.tomasz.restaurant.orders.application.query;

import com.beben.tomasz.restaurant.orders.domain.order.Order;
import com.beben.tomasz.restaurant.orders.domain.order.OrderId;
import com.beben.tomasz.restaurant.orders.domain.order.OrderStatus;
import com.beben.tomasz.restaurant.orders.domain.order.RestaurantId;
import com.beben.tomasz.restaurant.orders.domain.order.UserId;
import io.vavr.control.Option;

import java.util.List;

public interface OrderReadRepository {

    Option<Order> orderDetailsView(OrderId orderId);

    Option<Order> restaurantOrderDetailsView(OrderId orderId, RestaurantId restaurantReference);

    List<Order> findOrdersToTrack(RestaurantId restaurantReference, List<OrderStatus> orderStatuses, int page, int size);

    List<Order> findOrdersByUser(Option<UserId> userId, int page, int size);

    List<Order> findRestaurantOrders(RestaurantId restaurantId, int page, int size);
}
