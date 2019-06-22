package com.beben.tomasz.restaurant.orders.infrastructure.spring.persistance;

import com.beben.tomasz.restaurant.orders.domain.order.*;
import com.beben.tomasz.restaurant.orders.infrastructure.spring.persistance.write.OrderClientData;
import com.beben.tomasz.restaurant.orders.infrastructure.spring.persistance.write.OrderEntity;
import com.beben.tomasz.restaurant.orders.infrastructure.spring.persistance.write.OrderPaymentData;
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
    public static final String TEST_TABLE_REFERENCE = "TEST_TABLE_REFERENCE";
    public static final String TEST_RESTAURANT_REFERENCE = "TEST_RESTAURANT_REFERENCE";
    public static final String TEST_ORDER_ID = "TEST_ORDER_ID";
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

    public RestaurantOrder savePreparingOrder() {
        RestaurantOrder orderEntity = createOrder();
        Order order = orderEntity.prepare();

        entityManager.persist(order);

        return orderEntity;
    }

    public RestaurantOrder saveConfirmedOrder() {
        RestaurantOrder orderEntity = createOrder();
        Order order = orderEntity.confirm();

        entityManager.persist(order);

        return orderEntity;
    }

    public Order saveNewOrder() {
        Order orderEntity = createOrder();

        entityManager.persist(orderEntity);

        return orderEntity;
    }

    private RestaurantOrder createOrder() {
        return new RestaurantOrder(
                TEST_ORDER_ID,
                ContextHolderImpl.TEST_USER_ID,
                TEST_RESTAURANT_REFERENCE,
                TEST_TABLE_REFERENCE,
                Collections.singleton(OrderItem.of(
                        TEST_ORDER_ITEM_ID,
                        TEST_ORDER_ITEM_NAME,
                        TEST_ORDER_ITEM_PRICE,
                        TEST_ORDER_ITEM_QUANTITY
                )),
                OrderStatus.CREATED,
                TEST_ORDER_ITEM_PRICE,
                LocalTime.now(),
                OrderPaymentData.of(
                        "",
                        TEST_PAYMENT_TYPE
                ),
                OrderClientData.of(
                        TEST_CLIENT_NAME,
                        TEST_CLIENT_SURNAME,
                        TEST_CLIENT_EMAIL,
                        TEST_CLIENT_PHONE_NUMBER
                )
        );
    }

    public RestaurantOrder saveFinishOrder() {
        RestaurantOrder orderEntity = createOrder();
        Order finish = orderEntity.finish();

        entityManager.persist(finish);

        return orderEntity;
    }
}
