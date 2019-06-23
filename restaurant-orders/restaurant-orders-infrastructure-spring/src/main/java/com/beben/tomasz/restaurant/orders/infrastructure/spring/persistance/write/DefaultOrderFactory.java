package com.beben.tomasz.restaurant.orders.infrastructure.spring.persistance.write;

import com.beben.tomasz.restaurant.orders.domain.order.Order;
import com.beben.tomasz.restaurant.orders.domain.order.OrderClient;
import com.beben.tomasz.restaurant.orders.domain.order.OrderFactory;
import com.beben.tomasz.restaurant.orders.domain.order.OrderItem;
import com.beben.tomasz.restaurant.orders.domain.order.OrdersRepository;
import com.beben.tomasz.restaurant.orders.domain.order.PaymentType;
import com.beben.tomasz.restaurant.orders.domain.order.RestaurantId;
import com.beben.tomasz.restaurant.orders.domain.order.TableId;
import com.beben.tomasz.restaurant.orders.domain.order.UserId;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class DefaultOrderFactory implements OrderFactory {

    private OrdersRepository ordersRepository;

    @Override
    public Order createOrder(
            UserId userReference,
            RestaurantId restaurantReference,
            TableId tableReference,
            Set<OrderItem> orderItems,
            PaymentType paymentType,
            OrderClient client,
            LocalTime arrivalTime
    ) {
        Set<OrderItemEntity> orderItemEntities = collectOrderItem(orderItems);
        BigDecimal totalAmount = totalAmount(orderItemEntities);

        return OrderEntity.of(
                        ordersRepository.generateId(),
                        userReference.getId(),
                        restaurantReference.getId(),
                        tableReference.getId(),
                        orderItemEntities,
                        paymentType,
                        totalAmount,
                        mapClientData(client),
                        arrivalTime
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
