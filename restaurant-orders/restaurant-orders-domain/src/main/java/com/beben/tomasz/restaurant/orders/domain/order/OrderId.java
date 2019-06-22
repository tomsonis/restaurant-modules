package com.beben.tomasz.restaurant.orders.domain.order;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class OrderId implements Serializable {

    private String id;

    public static OrderId generateOrderId() {
        return new OrderId(UUID.randomUUID().toString());
    }
}
