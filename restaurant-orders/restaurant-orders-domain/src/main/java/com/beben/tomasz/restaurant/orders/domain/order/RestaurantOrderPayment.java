package com.beben.tomasz.restaurant.orders.domain.order;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RestaurantOrderPayment implements OrderPayment {

    private String referenceNumber;

    private PaymentType paymentType;

    private RestaurantOrderPayment(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public static RestaurantOrderPayment atWaiterPayment() {
        return new RestaurantOrderPayment(PaymentType.AT_WAITER);
    }

    public static RestaurantOrderPayment payuPayment(String referenceNumber) {
        return new RestaurantOrderPayment(referenceNumber, PaymentType.AT_WAITER);
    }
}
