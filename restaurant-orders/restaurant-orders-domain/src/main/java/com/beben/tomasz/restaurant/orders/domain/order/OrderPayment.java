package com.beben.tomasz.restaurant.orders.domain.order;

public interface OrderPayment {

    String getReferenceNumber();

    PaymentType getPaymentType();

}
