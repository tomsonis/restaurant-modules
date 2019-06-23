package com.beben.tomasz.restaurant.orders.domain.order.event;

import com.beben.tomasz.restaurant.orders.domain.order.Order;

public interface OrderEvent {

    void emmit(Order newOrder);
}
