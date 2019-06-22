package com.beben.tomasz.restaurant.orders.domain.order;


import io.vavr.control.Option;

public interface OrdersRepository {

    String generateId();

    Option<Order> save(Order order);

    Option<RestaurantOrder> readOrderToPay(OrderId orderId);

    Option<RestaurantOrder> readOrderToConfirm(OrderId orderId);

    Option<RestaurantOrder> readOrderToDelete(OrderId orderId);

    Option<RestaurantOrder> readOrderToPrepare(OrderId orderId);

    Option<RestaurantOrder> readOrderToFinish(OrderId orderId);

    Option<RestaurantOrder> readOrderToGive(OrderId orderId);
}
