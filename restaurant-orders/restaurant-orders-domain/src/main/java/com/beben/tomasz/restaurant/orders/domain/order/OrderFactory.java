package com.beben.tomasz.restaurant.orders.domain.order;

import io.vavr.control.Option;

import java.time.LocalTime;
import java.util.Set;

public interface OrderFactory {

    Option<Order> createOrder(
            String userReference,
            String restaurantReference,
            String tableReference,
            Set<OrderItem> orderItems,
            PaymentType paymentType,
            OrderClient client,
            LocalTime arrivalTime
    );
}
