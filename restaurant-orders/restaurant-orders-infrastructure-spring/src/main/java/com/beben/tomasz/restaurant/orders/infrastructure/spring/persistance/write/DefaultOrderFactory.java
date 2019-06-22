package com.beben.tomasz.restaurant.orders.infrastructure.spring.persistance.write;

import com.beben.tomasz.restaurant.orders.domain.order.*;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class DefaultOrderFactory implements OrderFactory {

    private OrdersRepository ordersRepository;

    @Override
    public Option<Order> createOrder(
            String userReference,
            String restaurantReference,
            String tableReference,
            Set<OrderItem> orderItems,
            PaymentType paymentType,
            OrderClient client,
            LocalTime arrivalTime
    ) {
        Set<OrderItemEntity> orderItemEntities = collectOrderItem(orderItems);
        BigDecimal totalAmount = totalAmount(orderItemEntities);

        return Option.of(
                OrderEntity.of(
                        ordersRepository.generateId(),
                        userReference,
                        restaurantReference,
                        tableReference,
                        orderItemEntities,
                        paymentType,
                        totalAmount,
                        mapClientData(client),
                        arrivalTime
                )
        );
    }

    private OrderClientData mapClientData(OrderClient client) {
        return OrderClientData.of(
                client.getName(),
                client.getSurname(),
                client.getEmail(),
                client.getPhoneNumber()
        );
    }

    private Set<OrderItemEntity> collectOrderItem(Set<OrderItem> orderItems) {
        return orderItems.stream()
                .map(orderItem -> new OrderItemEntity(
                        orderItem.getName(),
                        orderItem.getPrice(),
                        orderItem.getQuantity()
                ))
                .collect(Collectors.toSet());
    }

    private BigDecimal totalAmount(Set<OrderItemEntity> orderItemEntities) {
        return orderItemEntities.stream()
                .map(orderItemEntity ->
                        orderItemEntity.getPrice().multiply(BigDecimal.valueOf(orderItemEntity.getQuantity()))
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
