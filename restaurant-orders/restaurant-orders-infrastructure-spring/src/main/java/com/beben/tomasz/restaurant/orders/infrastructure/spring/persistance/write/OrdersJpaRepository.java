package com.beben.tomasz.restaurant.orders.infrastructure.spring.persistance.write;

import com.beben.tomasz.restaurant.orders.domain.order.Order;
import com.beben.tomasz.restaurant.orders.domain.order.OrderId;
import com.beben.tomasz.restaurant.orders.domain.order.OrderStatus;
import com.beben.tomasz.restaurant.orders.domain.order.OrdersRepository;
import com.beben.tomasz.restaurant.orders.domain.order.RestaurantOrder;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.UUID;


@Transactional
@AllArgsConstructor
public class OrdersJpaRepository implements OrdersRepository {

    private final EntityManager entityManager;

    @Override
    public String generateId() {
        return "ORD-" + UUID.randomUUID().toString();
    }

    @Override
    public Option<Order> save(Order order) {
        return readOrderEntity(order.getOrderId())
                .map(orderEntity -> updateEntity(order, orderEntity))
                .orElse(() -> Option.of(entityManager.merge(order)));
    }

    private Order updateEntity(Order orderToUpdate, OrderEntity existingOrder) {
        existingOrder.setOrderStatus(orderToUpdate.getOrderStatus());//TODO: actually more mapping is not required - extend in future

        return entityManager.merge(existingOrder);
    }

    @Override
    public Option<RestaurantOrder> readOrderToPay(OrderId orderId) {
        return readOrder(orderId, OrderStatus.CREATED);
    }

    @Override
    public Option<RestaurantOrder> readOrderToConfirm(OrderId orderId) {
        return readOrder(orderId, OrderStatus.CREATED);
    }

    @Override
    public Option<RestaurantOrder> readOrderToDelete(OrderId orderId) {
        return readOrder(orderId, OrderStatus.CREATED);
    }

    @Override
    public Option<RestaurantOrder> readOrderToPrepare(OrderId orderId) {
        return readOrder(orderId, OrderStatus.CONFIRMED);
    }

    @Override
    public Option<RestaurantOrder> readOrderToFinish(OrderId orderId) {
        return readOrder(orderId, OrderStatus.PREPARING);
    }

    @Override
    public Option<RestaurantOrder> readOrderToGive(OrderId orderId) {
        return readOrder(orderId, OrderStatus.DONE);
    }

    private Option<RestaurantOrder> readOrder(OrderId orderId, OrderStatus orderStatus) {
            return readOrderEntityByIdAndStatus(orderId, orderStatus)
                    .map(orderEntity -> new RestaurantOrder(
                            orderEntity.getOrderId(),
                            orderEntity.getUserReference(),
                            orderEntity.getRestaurantReference(),
                            orderEntity.getTableReference(),
                            orderEntity.getOrderItemEntities(),
                            orderEntity.getOrderStatus(),
                            orderEntity.getTotalAmount(),
                            orderEntity.getArrivalTime(),
                            orderEntity.getOrderPayment(),
                            orderEntity.getClientData()
                    )
            );
    }

    private Option<Order> readOrderEntityByIdAndStatus(OrderId orderId, OrderStatus orderStatus) {
        return Try.of(() -> entityManager.createQuery(
                "select o from OrderEntity o where o.id = :id and o.orderStatus = :status",
                Order.class
                )
                        .setParameter("id", orderId.getId())
                        .setParameter("status", orderStatus)
                        .getSingleResult()
        ).toOption();
    }

    private Option<OrderEntity> readOrderEntity(OrderId orderId) {
        return Option.of(entityManager.find(OrderEntity.class, orderId.getId()));
    }
}
