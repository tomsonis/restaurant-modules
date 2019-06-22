package com.beben.tomasz.restaurant.orders.infrastructure.spring.persistance.write;

import com.beben.tomasz.restaurant.orders.domain.order.*;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.UUID;


@Transactional
@AllArgsConstructor
public class JpaOrdersWriteRepository implements OrdersRepository {

    private final EntityManager entityManager;

    @Override
    public String generateId() {
        return "ORD-" + UUID.randomUUID().toString();
    }

    @Override
    public Option<Order> save(Order order) {
        entityManager.persist(order);

        return Option.some(order);
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
        try {
            OrderEntity orderEntity = entityManager.createQuery(
                    "select o from OrderEntity o where o.id = :id and o.orderStatus = :status",
                    OrderEntity.class
            )
                    .setParameter("id", orderId.getId())
                    .setParameter("status", orderStatus)
                    .getSingleResult();

            return Option.of(new RestaurantOrder(
                            orderEntity.getId(),
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
        } catch (NoResultException e) {
            return Option.none();
        }
    }
}
