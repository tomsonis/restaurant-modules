package com.beben.tomasz.restaurant.orders.domain.order;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class OrderItem {

    @NotBlank
    private String id;

    @NotBlank
    private String name;

    @NotNull
    private BigDecimal price;

    @Min(1)
    private int quantity;

    public BigDecimal totalPrice() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}
