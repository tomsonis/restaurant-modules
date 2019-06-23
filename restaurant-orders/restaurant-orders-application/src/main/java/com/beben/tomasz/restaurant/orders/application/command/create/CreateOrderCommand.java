package com.beben.tomasz.restaurant.orders.application.command.create;


import com.beben.tomasz.cqrs.api.command.Command;
import com.beben.tomasz.restaurant.orders.domain.order.OrderClient;
import com.beben.tomasz.restaurant.orders.domain.order.OrderId;
import com.beben.tomasz.restaurant.orders.domain.order.OrderItem;
import com.beben.tomasz.restaurant.orders.domain.order.PaymentType;
import com.beben.tomasz.restaurant.orders.domain.order.RestaurantId;
import com.beben.tomasz.restaurant.orders.domain.order.TableId;
import io.vavr.control.Option;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    @NotNull
    private RestaurantId restaurantReference;

    @NotNull
    private TableId tableReference;

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

    public CreateOrderCommand(@NotBlank TableId tableReference, @Valid @NotEmpty @Size(min = 1) Set<OrderItem> orderItems, @NotNull PaymentType paymentType, @NotNull @Valid OrderClient client, String arrivalTime) {
        this.tableReference = tableReference;
        this.orderItems = orderItems;
        this.paymentType = paymentType;
        this.client = client;
        this.arrivalTime = LocalTime.parse(arrivalTime);
    }
}
