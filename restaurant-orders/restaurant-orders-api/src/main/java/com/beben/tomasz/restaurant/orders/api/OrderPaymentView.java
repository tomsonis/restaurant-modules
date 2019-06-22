package com.beben.tomasz.restaurant.orders.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class OrderPaymentView {

    private String reference;

    private PaymentTypeView paymentType;
}
