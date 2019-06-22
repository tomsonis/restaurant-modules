package com.beben.tomasz.restaurant.orders.infrastructure.spring.persistance.read;

import com.beben.tomasz.restaurant.orders.application.query.OrderReadRepository;
import com.beben.tomasz.restaurant.orders.domain.order.Order;
import com.beben.tomasz.restaurant.orders.domain.order.OrderStatus;
import io.vavr.control.Option;
import io.vavr.control.Try;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class JpaReadOrdersRepository implements OrderReadRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Option<Order> orderDetailsView(String orderId, String userReference) {
        return Try.of(() -> entityManager.createQuery(
                "select o from OrderEntity o where o.id = :orderId",
                Order.class
                )
                        .setParameter("orderId", orderId)
                        .getSingleResult()
        )
                .map(Option::of)
                .getOrElse(Option.none());
    }

    @Override
    public Option<Order> restaurantOrderDetailsView(String orderId, String restaurantReference) {
        return Try.of(() ->
                        entityManager.createQuery(
                                        "select o from OrderEntity o where o.id = :orderId AND o.restaurantReference = :restaurantReference",
                                        Order.class
                                )
                                .setParameter("orderId", orderId)
                                .setParameter("restaurantReference", restaurantReference)
                                .getSingleResult()
                )
                .map(Option::of)
                .getOrElse(Option.none());
    }

    @Override
    public List<Order> findOrdersToTrack(String restaurantReference, List<OrderStatus> orderStatuses, int page, int size) {
        return entityManager.createQuery(
                "select o from OrderEntity o WHERE o.orderStatus IN :orderStatuses " +
                        "AND o.restaurantReference = :restaurantReference order by o.updatedAt desc",
                Order.class
        )
                .setParameter("orderStatuses", orderStatuses)
                .setParameter("restaurantReference", restaurantReference)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public List<Order> findOrdersByUser(Option<String> userReference, int page, int size) {
        return entityManager.createQuery(
                "select o from OrderEntity o WHERE " +
                        "o.userReference = :userReference order by o.createdAt desc",
                Order.class
        )
                .setParameter("userReference", userReference)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public List<Order> findRestaurantOrders(String restaurantId, int page, int size) {
        return entityManager.createQuery(
                "select o from OrderEntity o WHERE " +
                        "o.restaurantReference = :restaurantReference order by o.createdAt desc",
                Order.class
        )
                .setParameter("restaurantReference", restaurantId)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }
}
