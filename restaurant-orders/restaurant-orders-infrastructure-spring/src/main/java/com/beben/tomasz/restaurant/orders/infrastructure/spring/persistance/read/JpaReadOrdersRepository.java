package com.beben.tomasz.restaurant.orders.infrastructure.spring.persistance.read;

import com.beben.tomasz.restaurant.orders.application.query.OrderReadRepository;
import com.beben.tomasz.restaurant.orders.domain.order.Order;
import com.beben.tomasz.restaurant.orders.domain.order.OrderId;
import com.beben.tomasz.restaurant.orders.domain.order.OrderStatus;
import com.beben.tomasz.restaurant.orders.domain.order.RestaurantId;
import com.beben.tomasz.restaurant.orders.domain.order.UserId;
import io.vavr.control.Option;
import io.vavr.control.Try;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class JpaReadOrdersRepository implements OrderReadRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Option<Order> orderDetailsView(OrderId orderId) {
        return Try.of(() -> entityManager.createQuery(
                "select o from OrderEntity o where o.id = :orderId",
                Order.class
                )
                        .setParameter("orderId", orderId.getId())
                        .getSingleResult()
        )
                .map(Option::of)
                .getOrElse(Option.none());
    }

    @Override
    public Option<Order> restaurantOrderDetailsView(OrderId orderId, RestaurantId restaurantReference) {
        return Try.of(() ->
                        entityManager.createQuery(
                                        "select o from OrderEntity o where o.id = :orderId AND o.restaurantReference = :restaurantReference",
                                        Order.class
                                )
                                .setParameter("orderId", orderId.getId())
                                .setParameter("restaurantReference", restaurantReference.getId())
                                .getSingleResult()
                )
                .map(Option::of)
                .getOrElse(Option.none());
    }

    @Override
    public List<Order> findOrdersToTrack(RestaurantId restaurantReference, List<OrderStatus> orderStatuses, int page, int size) {
        return entityManager.createQuery(
                "select o from OrderEntity o WHERE o.orderStatus IN :orderStatuses " +
                        "AND o.restaurantReference = :restaurantReference order by o.updatedAt desc",
                Order.class
        )
                .setParameter("orderStatuses", orderStatuses)
                .setParameter("restaurantReference", restaurantReference.getId())
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public List<Order> findOrdersByUser(Option<UserId> userReference, int page, int size) {
        return entityManager.createQuery(
                "select o from OrderEntity o WHERE " +
                        "o.userReference = :userReference order by o.createdAt desc",
                Order.class
        )
                .setParameter("userReference", userReference.map(UserId::getId).getOrElse(""))
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public List<Order> findRestaurantOrders(RestaurantId restaurantId, int page, int size) {
        return entityManager.createQuery(
                "select o from OrderEntity o WHERE " +
                        "o.restaurantReference = :restaurantReference order by o.createdAt desc",
                Order.class
        )
                .setParameter("restaurantReference", restaurantId.getId())
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }
}
