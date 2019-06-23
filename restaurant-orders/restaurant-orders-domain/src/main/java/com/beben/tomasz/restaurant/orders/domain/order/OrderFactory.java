package com.beben.tomasz.restaurant.orders.domain.order;

import java.time.LocalTime;
import java.util.Set;

public interface OrderFactory {

    Order createOrder(
            UserId userReference,
            RestaurantId restaurantReference,
            TableId tableReference,
            Set<OrderItem> orderItems,
            PaymentType paymentType,
            OrderClient client,
            LocalTime arrivalTime
    );
}
