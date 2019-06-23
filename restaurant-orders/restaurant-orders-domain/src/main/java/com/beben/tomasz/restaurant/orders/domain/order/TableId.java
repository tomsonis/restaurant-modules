package com.beben.tomasz.restaurant.orders.domain.order;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class TableId implements Serializable {

    @NotBlank
    private String id;

    public static TableId generateOrderId() {
        return new TableId(UUID.randomUUID().toString());
    }
}
