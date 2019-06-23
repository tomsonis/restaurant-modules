package com.beben.tomasz.restaurant.orders.domain.order;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(staticName = "empty")
@AllArgsConstructor(staticName = "of")
public class UserId implements Serializable {

    private String id;

    public static UserId generateOrderId() {
        return new UserId(UUID.randomUUID().toString());
    }
}
