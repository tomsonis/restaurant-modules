package com.beben.tomasz.restaurant.orders.api;

import lombok.*;

import java.math.BigDecimal;


@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class OrderItemView {

    private String id;

    private String name;

    private BigDecimal price;

    private int quantity;

}
