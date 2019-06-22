package com.beben.tomasz.restaurant.orders.infrastructure.spring.delivery;

import com.beben.tomasz.restaurant.orders.BaseOrdersIntegrationTest;
import com.beben.tomasz.restaurant.orders.api.OrderDetailsView;
import com.beben.tomasz.restaurant.orders.application.command.create.CreateOrderCommand;
import com.beben.tomasz.restaurant.orders.application.query.user.details.OrderDetailsQuery;
import com.beben.tomasz.restaurant.orders.domain.order.*;
import com.beben.tomasz.restaurant.orders.infrastructure.spring.persistance.TestOrdersDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OrdersHttpEndpointTest extends BaseOrdersIntegrationTest {

    @Autowired
    private TestOrdersDatabase testOrdersDatabase;

    @AfterMethod
    public void tearDown() {
        testOrdersDatabase.clearAll();
    }

    @Test
    public void shouldCreateOrder() throws URISyntaxException {
        CreateOrderCommand createOrderCommand = CreateOrderCommand.of(
                TestOrdersDatabase.TEST_RESTAURANT_REFERENCE,
                TestOrdersDatabase.TEST_TABLE_REFERENCE,
                Collections.singleton(OrderItem.of(
                        TestOrdersDatabase.TEST_ORDER_ITEM_ID,
                        TestOrdersDatabase.TEST_ORDER_ITEM_NAME,
                        TestOrdersDatabase.TEST_ORDER_ITEM_PRICE,
                        TestOrdersDatabase.TEST_ORDER_ITEM_QUANTITY

                )),
                TestOrdersDatabase.TEST_PAYMENT_TYPE,
                OrderClient.of(
                        TestOrdersDatabase.TEST_CLIENT_NAME,
                        TestOrdersDatabase.TEST_CLIENT_SURNAME,
                        TestOrdersDatabase.TEST_CLIENT_EMAIL,
                        TestOrdersDatabase.TEST_CLIENT_PHONE_NUMBER
                ),
                TestOrdersDatabase.TEST_ARRIVAL_TIME
        );

        Response response = client().target(getRestUri())
                .path("orders")
                .path("create")
                .request()
                .post(Entity.json(createOrderCommand));

        //then
        assertThat(response.getStatus()).isEqualTo(200);

        OrderId orderId = response.readEntity(OrderId.class);
        assertThat(orderId).isNotNull();
        assertThat(orderId.getId()).isNotBlank();

        Order order = testOrdersDatabase.findById(orderId);
        assertThat(order).isNotNull();
        assertThat(order.getRestaurantReference()).isEqualTo(createOrderCommand.getRestaurantReference());
        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.CREATED);
    }

    @Test
    public void testOrderDetails() throws URISyntaxException {
        //given
        Order order = testOrdersDatabase.saveNewOrder();

        OrderDetailsQuery orderDetailsQuery = OrderDetailsQuery.of(OrderId.of(order.getId()));

        //when
        Response response = client().target(getRestUri())
                .path("orders")
                .path("details")
                .request()
                .post(Entity.json(orderDetailsQuery));


        //then
        assertThat(response.getStatus()).isEqualTo(200);

        OrderDetailsView orderDetailsView = response.readEntity(OrderDetailsView.class);
        assertThat(orderDetailsView).isNotNull();
        assertThat(orderDetailsView.getId()).isEqualTo(order.getId());
        assertThat(orderDetailsView.getArrivalTime().getHour()).isEqualTo(order.getArrivalTime().getHour());
        assertThat(orderDetailsView.getArrivalTime().getMinute()).isEqualTo(order.getArrivalTime().getMinute());
    }

    @Test
    public void testOrderListByUser() throws URISyntaxException {

        //given
        Order order = testOrdersDatabase.saveNewOrder();

        //when
        Response response = client().target(getRestUri())
                .path("orders")
                .path("list")
                .request()
                .get();


        //then
        assertThat(response.getStatus()).isEqualTo(200);

        List<OrderDetailsView> orderDetailsViews = response.readEntity(new GenericType<List<OrderDetailsView>>(){});

        assertThat(orderDetailsViews).isNotNull();
        assertThat(orderDetailsViews).hasSizeGreaterThan(0);

        OrderDetailsView orderDetailsView = orderDetailsViews.get(0);
        assertThat(orderDetailsView.getId()).isEqualTo(order.getId());
        assertThat(orderDetailsView.getArrivalTime().getHour()).isEqualTo(order.getArrivalTime().getHour());
        assertThat(orderDetailsView.getArrivalTime().getMinute()).isEqualTo(order.getArrivalTime().getMinute());
    }
}