package com.beben.tomasz.restaurant.orders.application.model;

import com.beben.tomasz.restaurant.orders.domain.order.OrderPayment;
import com.beben.tomasz.restaurant.orders.domain.order.PaymentType;

public class TestOrderPayment implements OrderPayment {

    public static final String TEST_PAYMENT_REFERENCE_NUMBER = "TEST_PAYMENT_REFERENCE_NUMBER";
    public static final PaymentType TEST_PAYMENT_TYPE = PaymentType.PAY_U;

    @Override
    public String getReferenceNumber() {
        return TEST_PAYMENT_REFERENCE_NUMBER;
    }

    @Override
    public PaymentType getPaymentType() {
        return TEST_PAYMENT_TYPE;
    }
}
