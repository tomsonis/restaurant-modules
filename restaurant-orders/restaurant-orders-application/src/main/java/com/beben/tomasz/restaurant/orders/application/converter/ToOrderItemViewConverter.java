package com.beben.tomasz.restaurant.orders.application.converter;

import com.beben.tomasz.restaurant.commons.Converter;
import com.beben.tomasz.restaurant.orders.api.OrderItemView;
import com.beben.tomasz.restaurant.orders.domain.order.OrderItem;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ToOrderItemViewConverter implements Converter<OrderItem, OrderItemView> {

    @Override
    public OrderItemView convert(OrderItem orderItem) {
        return OrderItemView.of(
                orderItem.getId(),
                orderItem.getName(),
                orderItem.getPrice(),
                orderItem.getQuantity()
        );
    }
}
