package com.beben.tomasz.restaurant.orders.application.query;

import com.beben.tomasz.restaurant.orders.domain.order.Order;
import com.beben.tomasz.restaurant.orders.domain.order.OrderStatus;
import io.vavr.control.Option;

import java.util.List;

public interface OrderReadRepository {

    Option<Order> orderDetailsView(String orderId, String userReference);

    Option<Order> restaurantOrderDetailsView(String orderId, String restaurantReference);

    List<Order> findOrdersToTrack(String restaurantReference, List<OrderStatus> orderStatuses, int page, int size);

    List<Order> findOrdersByUser(Option<String> userId, int page, int size);

    List<Order> findRestaurantOrders(String restaurantId, int page, int size);
}
