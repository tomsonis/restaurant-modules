package com.beben.tomasz.restaurant.orders.domain.order.event;

import com.beben.tomasz.restaurant.orders.domain.order.Order;
import com.beben.tomasz.restaurant.orders.domain.order.RestaurantOrder;

public interface OrderEvent {

    void emmit(Order newOrder);

    void emmit(RestaurantOrder confirmOrder);
}
