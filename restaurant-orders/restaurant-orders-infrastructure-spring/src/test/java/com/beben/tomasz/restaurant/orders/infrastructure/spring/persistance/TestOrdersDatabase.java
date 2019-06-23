package com.beben.tomasz.restaurant.orders.infrastructure.spring.persistance;

import com.beben.tomasz.restaurant.orders.domain.order.Order;
import com.beben.tomasz.restaurant.orders.domain.order.OrderId;
import com.beben.tomasz.restaurant.orders.domain.order.OrderStatus;
import com.beben.tomasz.restaurant.orders.domain.order.PaymentType;
import com.beben.tomasz.restaurant.orders.domain.order.RestaurantId;
import com.beben.tomasz.restaurant.orders.domain.order.TableId;
import com.beben.tomasz.restaurant.orders.infrastructure.spring.persistance.write.OrderClientData;
import com.beben.tomasz.restaurant.orders.infrastructure.spring.persistance.write.OrderEntity;
import com.beben.tomasz.restaurant.orders.infrastructure.spring.persistance.write.OrderItemEntity;
import com.beben.tomasz.restaurant.test.module.ContextHolderImpl;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Collections;

@Transactional
@AllArgsConstructor
public class TestOrdersDatabase {

    public static final LocalTime TEST_ARRIVAL_TIME = LocalTime.now().plusMinutes(40);
    public static final String TEST_CLIENT_NAME = "TEST_CLIENT_NAME";
    public static final String TEST_CLIENT_SURNAME = "TEST_CLIENT_SURNAME";
    public static final String TEST_CLIENT_EMAIL = "test@email.com";
    public static final String TEST_CLIENT_PHONE_NUMBER = "000000000";
    public static final TableId TEST_TABLE_REFERENCE = TableId.of("TEST_TABLE_REFERENCE");
    public static final RestaurantId TEST_RESTAURANT_REFERENCE = RestaurantId.of("TEST_RESTAURANT_REFERENCE");
    public static final OrderId TEST_ORDER_ID = OrderId.of("TEST_ORDER_ID");
    public static final PaymentType TEST_PAYMENT_TYPE = PaymentType.PAY_U;

    public static final String TEST_ORDER_ITEM_ID = "TEST_ORDER_ITEM_ID";
    public static final String TEST_ORDER_ITEM_NAME = "TEST_ORDER_ITEM_NAME";
    public static final BigDecimal TEST_ORDER_ITEM_PRICE = BigDecimal.TEN;
    public static final int TEST_ORDER_ITEM_QUANTITY = 1;


    private EntityManager entityManager;

    public void clearAll() {
        entityManager.createQuery("DELETE FROM OrderItemEntity").executeUpdate();
        entityManager.createQuery("DELETE FROM OrderEntity").executeUpdate();
    }

    public Order findById(OrderId orderId) {
        return entityManager.find(OrderEntity.class, orderId.getId());
    }

    public Order savePreparingOrder() {
        Order order = createOrder(OrderStatus.PREPARING);

        entityManager.persist(order);

        return order;
    }

    public Order saveConfirmedOrder() {
        Order order = createOrder(OrderStatus.CONFIRMED);

        entityManager.persist(order);

        return order;
    }

    public Order saveNewOrder() {
        Order orderEntity = createOrder(OrderStatus.CREATED);

        entityManager.persist(orderEntity);

        return orderEntity;
    }

    private OrderEntity createOrder(OrderStatus orderStatus) {
        return new OrderEntity(
                TEST_ORDER_ID.getId(),
                ContextHolderImpl.TEST_USER_ID,
                TEST_RESTAURANT_REFERENCE.getId(),
                TEST_TABLE_REFERENCE.getId(),
                Collections.singleton(new OrderItemEntity(
                        TEST_ORDER_ITEM_NAME,
                        TEST_ORDER_ITEM_PRICE,
                        TEST_ORDER_ITEM_QUANTITY
                )),
                orderStatus,
                TEST_PAYMENT_TYPE,
                TEST_ORDER_ITEM_PRICE,
                OrderClientData.of(
                        TEST_CLIENT_NAME,
                        TEST_CLIENT_SURNAME,
                        TEST_CLIENT_EMAIL,
                        TEST_CLIENT_PHONE_NUMBER
                ),
                LocalTime.now()
        );
    }

    public Order saveFinishOrder() {
        Order order = createOrder(OrderStatus.DONE);

        entityManager.persist(order);

        return order;
    }
}
