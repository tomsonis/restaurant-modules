package com.beben.tomasz.restaurant.orders.api.command;

import com.beben.tomasz.cqrs.api.command.Command;
import io.vavr.control.Option;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class PayOrderCommand implements Command<Void> {

    @NotEmpty
    private String orderId;

    private Option<String> paymentReferenceNumber;//todo: Sprawdzc jak się serializuje
}
