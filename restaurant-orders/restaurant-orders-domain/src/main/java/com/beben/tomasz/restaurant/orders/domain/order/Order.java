package com.beben.tomasz.restaurant.orders.domain.order;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Set;

public interface Order {

    String getId();

    String getUserReference();

    String getRestaurantReference();

    String getTableReference();

    Set<OrderItem> getOrderItemEntities();

    OrderStatus getOrderStatus();

    BigDecimal getTotalAmount();

    OrderPayment getOrderPayment();

    ClientData getClientData();

    LocalTime getArrivalTime();
}
