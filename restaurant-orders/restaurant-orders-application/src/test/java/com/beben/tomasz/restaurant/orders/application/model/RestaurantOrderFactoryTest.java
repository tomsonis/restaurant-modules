package com.beben.tomasz.restaurant.orders.application.model;

import com.beben.tomasz.restaurant.orders.domain.order.OrderClient;
import com.beben.tomasz.restaurant.orders.domain.order.OrderId;
import com.beben.tomasz.restaurant.orders.domain.order.OrderItem;
import com.beben.tomasz.restaurant.orders.domain.order.OrderStatus;
import com.beben.tomasz.restaurant.orders.domain.order.RestaurantId;
import com.beben.tomasz.restaurant.orders.domain.order.RestaurantOrder;
import com.beben.tomasz.restaurant.orders.domain.order.RestaurantOrderPayment;
import com.beben.tomasz.restaurant.orders.domain.order.TableId;
import com.beben.tomasz.restaurant.orders.domain.order.UserId;
import io.vavr.control.Option;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Set;

@Getter
public class RestaurantOrderFactoryTest {

    public static final LocalTime TEST_ARRIVAL_TIME = LocalTime.now().plusMinutes(40);
    public static final String TEST_CLIENT_NAME = "TEST_CLIENT_NAME";
    public static final String TEST_CLIENT_SURNAME = "TEST_CLIENT_SURNAME";
    public static final String TEST_CLIENT_EMAIL = "test@email.com";
    public static final String TEST_CLIENT_PHONE_NUMBER = "000000000";
    public static final TableId TEST_TABLE_REFERENCE = TableId.of("TEST_TABLE_REFERENCE");
    public static final RestaurantId TEST_RESTAURANT_REFERENCE = RestaurantId.of("TEST_RESTAURANT_REFERENCE");
    public static final UserId TEST_USER_REFERENCE = UserId.of("TEST_USER_REFERENCE");
    public static final OrderId TEST_ORDER_ID = OrderId.of("TEST_ORDER_ID");
    public static final String TEST_ORDER_ITEM_ID = "TEST_ORDER_ITEM_ID";
    public static final String TEST_ORDER_ITEM_NAME = "TEST_ORDER_ITEM_NAME";

    public static Option<RestaurantOrder> createOrder() {
        return Option.of(new RestaurantOrder(
                TEST_ORDER_ID,
                TEST_USER_REFERENCE,
                TEST_RESTAURANT_REFERENCE,
                TEST_TABLE_REFERENCE,
                orderItems(),
                OrderStatus.CREATED,
                BigDecimal.TEN,
                TEST_ARRIVAL_TIME,
                RestaurantOrderPayment.atWaiterPayment(),
                getClientData()
        ));
    }

    static OrderClient getClientData() {
        return OrderClient.of(
                TEST_CLIENT_NAME,
                TEST_CLIENT_SURNAME,
                TEST_CLIENT_EMAIL,
                TEST_CLIENT_PHONE_NUMBER
        );
    }

    public static Set<OrderItem> orderItems() {
        return Collections.singleton(OrderItem.of(
                TEST_ORDER_ITEM_ID,
                TEST_ORDER_ITEM_NAME,
                BigDecimal.ZERO,
                1
        ));
    }
}
