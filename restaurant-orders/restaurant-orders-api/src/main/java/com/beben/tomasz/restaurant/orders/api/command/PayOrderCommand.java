package com.beben.tomasz.restaurant.orders.api.command;

import com.beben.tomasz.cqrs.api.command.Command;
import com.beben.tomasz.restaurant.orders.domain.order.OrderId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class PayOrderCommand implements Command<Void> {

    @NotNull
    private OrderId orderId;

    private String paymentReferenceNumber;//todo: Sprawdzc jak się serializuje
}
