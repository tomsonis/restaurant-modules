package com.beben.tomasz.restaurant.orders.domain.order;

import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Set;

@Getter
@AllArgsConstructor
public class RestaurantOrder implements Order {

    private String id;

    private String userReference;

    private String restaurantReference;

    private String tableReference;

    private Set<OrderItem> orderItemEntities;

    private OrderStatus orderStatus;

    private BigDecimal totalAmount;

    private LocalTime arrivalTime;

    private OrderPayment orderPayment;

    private ClientData clientData;

    public RestaurantOrder pay(Option<String> referenceNumber) {
        this.orderPayment = referenceNumber
                .map(paymentReferenceNumber -> RestaurantOrderPayment.payuPayment(restaurantReference))
                .getOrElse(RestaurantOrderPayment.atWaiterPayment());

        return updateStatus(OrderStatus.PAID);
    }

    public RestaurantOrder confirm() {
        return updateStatus(OrderStatus.CONFIRMED);
    }

    public RestaurantOrder finish() {
        return updateStatus(OrderStatus.DONE);
    }

    public RestaurantOrder prepare() {
        return updateStatus(OrderStatus.PREPARING);
    }

    public RestaurantOrder delete() {
        return updateStatus(OrderStatus.DELETED);
    }

    public RestaurantOrder marksAsGiven() {
        return updateStatus(OrderStatus.GIVEN);
    }

    public RestaurantOrder pay() {
        return updateStatus(OrderStatus.PAID);
    }

    private RestaurantOrder updateStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }
}
