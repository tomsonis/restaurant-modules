package com.beben.tomasz.restaurant.orders.application.command.create;


import com.beben.tomasz.restaurant.commons.Rejection;
import com.beben.tomasz.cqrs.api.command.Command;
import com.beben.tomasz.restaurant.orders.domain.order.OrderClient;
import com.beben.tomasz.restaurant.orders.domain.order.OrderId;
import com.beben.tomasz.restaurant.orders.domain.order.OrderItem;
import com.beben.tomasz.restaurant.orders.domain.order.PaymentType;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalTime;
import java.util.Set;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class CreateOrderCommand implements Command<Option<OrderId>> {

    @NotBlank
    private String restaurantReference;

    @NotBlank
    private String tableReference;

    @Valid
    @NotEmpty
    @Size(min = 1)
    private Set<OrderItem> orderItems;

    @NotNull
    private PaymentType paymentType;

    @NotNull
    @Valid
    private OrderClient client;

    private LocalTime arrivalTime;

    public CreateOrderCommand(@NotBlank String tableReference, @Valid @NotEmpty @Size(min = 1) Set<OrderItem> orderItems, @NotNull PaymentType paymentType, @NotNull @Valid OrderClient client, String arrivalTime) {
        this.tableReference = tableReference;
        this.orderItems = orderItems;
        this.paymentType = paymentType;
        this.client = client;
        this.arrivalTime = LocalTime.parse(arrivalTime);
    }
}
