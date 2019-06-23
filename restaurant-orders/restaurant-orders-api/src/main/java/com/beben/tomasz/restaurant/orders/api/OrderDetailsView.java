package com.beben.tomasz.restaurant.orders.api;

import com.beben.tomasz.restaurant.core.api.view.RestaurantTableView;
import com.beben.tomasz.restaurant.core.api.view.RestaurantView;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Set;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class OrderDetailsView implements Serializable {

    private String id;

    private Set<OrderItemView> orderItems;

    private OrderStatusView orderStatus;

    private OrderPaymentView orderPayment;

    private RestaurantView restaurant;

    private RestaurantTableView restaurantTable;

    private ClientView client;

    private BigDecimal totalAmount;

    private LocalTime arrivalTime;
}
