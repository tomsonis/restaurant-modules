package com.beben.tomasz.restaurant.orders.application.converter;

import com.beben.tomasz.restaurant.commons.Converter;
import com.beben.tomasz.restaurant.orders.api.OrderItemView;
import com.beben.tomasz.restaurant.orders.domain.order.OrderItem;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderItemConverter implements Converter<OrderItemView, OrderItem> {

    @Override
    public OrderItem convert(OrderItemView orderItemView) {
        return OrderItem.of(
                orderItemView.getId(),
                orderItemView.getName(),
                orderItemView.getPrice(),
                orderItemView.getQuantity()
        );
    }
}
