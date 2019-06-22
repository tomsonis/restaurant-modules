package com.beben.tomasz.restaurant.orders.infrastructure.spring.persistance.write;

import com.beben.tomasz.restaurant.orders.domain.order.OrderPayment;
import com.beben.tomasz.restaurant.orders.domain.order.PaymentType;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
@Embeddable
public class OrderPaymentData implements OrderPayment {

    private String referenceNumber;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    public OrderPaymentData(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}
