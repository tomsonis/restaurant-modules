package com.beben.tomasz.restaurant.orders.domain.order;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Set;

public interface Order {

    OrderId getOrderId();

    UserId getUserReference();

    RestaurantId getRestaurantReference();

    TableId getTableReference();

    Set<OrderItem> getOrderItemEntities();

    OrderStatus getOrderStatus();

    BigDecimal getTotalAmount();

    OrderPayment getOrderPayment();

    ClientData getClientData();

    LocalTime getArrivalTime();
}
