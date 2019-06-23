package com.beben.tomasz.restaurant.orders.api.request;

import com.beben.tomasz.restaurant.orders.api.OrderItemView;
import com.beben.tomasz.restaurant.orders.api.PaymentTypeView;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class CreateOrderRequest implements Serializable {

    @NotBlank
    private String restaurantReference;

    @NotBlank
    private String tableReference;

    @NotEmpty
    @Size(min = 1)
    private Set<OrderItemView> orderItems;

    @NotNull
    private PaymentTypeView paymentType;
}
