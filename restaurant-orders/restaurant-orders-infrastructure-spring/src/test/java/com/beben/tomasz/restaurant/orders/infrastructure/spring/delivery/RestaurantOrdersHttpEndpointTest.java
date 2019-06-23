package com.beben.tomasz.restaurant.orders.infrastructure.spring.delivery;

import com.beben.tomasz.restaurant.orders.BaseOrdersIntegrationTest;
import com.beben.tomasz.restaurant.orders.api.OrderDetailsView;
import com.beben.tomasz.restaurant.orders.api.command.ConfirmOrderCommand;
import com.beben.tomasz.restaurant.orders.api.command.PrepareOrderCommand;
import com.beben.tomasz.restaurant.orders.application.query.restaurant.RestaurantOrderDetailsQuery;
import com.beben.tomasz.restaurant.orders.domain.order.Order;
import com.beben.tomasz.restaurant.orders.domain.order.OrderStatus;
import com.beben.tomasz.restaurant.orders.infrastructure.spring.persistance.TestOrdersDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantOrdersHttpEndpointTest extends BaseOrdersIntegrationTest {

    @Autowired
    private TestOrdersDatabase testOrdersDatabase;

    @AfterMethod
    public void tearDown() {
        testOrdersDatabase.clearAll();
    }

    @Test
    public void testRestaurantOrderDetails() throws URISyntaxException {

        //given
        Order order = testOrdersDatabase.saveNewOrder();
        RestaurantOrderDetailsQuery restaurantOrderDetailsQuery = RestaurantOrderDetailsQuery.of(order.getOrderId());

        //when
        Response response = client().target(getRestUri())
                .path("restaurant/orders")
                .path("details")
                .request()
                .post(Entity.json(restaurantOrderDetailsQuery));

        //then
        assertThat(response.getStatus()).isEqualTo(200);

        OrderDetailsView orderDetailsView = response.readEntity(OrderDetailsView.class);
        assertThat(orderDetailsView).isNotNull();
        assertThat(orderDetailsView.getId()).isEqualTo(order.getOrderId().getId());
        assertThat(orderDetailsView.getArrivalTime().getHour()).isEqualTo(order.getArrivalTime().getHour());
        assertThat(orderDetailsView.getArrivalTime().getMinute()).isEqualTo(order.getArrivalTime().getMinute());
    }

    @Test
    public void testRestaurantOrders() throws URISyntaxException {

        //given
        Order order = testOrdersDatabase.saveNewOrder();

        //when
        Response response = client().target(getRestUri())
                .path("restaurant/orders")
                .path("list")
                .request()
                .get();


        //then
        assertThat(response.getStatus()).isEqualTo(200);

        List<OrderDetailsView> orderDetailsViews = response.readEntity(new GenericType<List<OrderDetailsView>>(){});

        assertThat(orderDetailsViews).isNotNull();
        assertThat(orderDetailsViews).hasSizeGreaterThan(0);

        OrderDetailsView orderDetailsView = orderDetailsViews.get(0);
        assertThat(orderDetailsView.getId()).isEqualTo(order.getOrderId().getId());
        assertThat(orderDetailsView.getArrivalTime().getHour()).isEqualTo(order.getArrivalTime().getHour());
        assertThat(orderDetailsView.getArrivalTime().getMinute()).isEqualTo(order.getArrivalTime().getMinute());
    }

    @Test
    public void testConfirmOrder() throws URISyntaxException {
        //given
        Order saveNewOrder = testOrdersDatabase.saveNewOrder();

        ConfirmOrderCommand restaurantOrderDetailsQuery = ConfirmOrderCommand.of(
                saveNewOrder.getOrderId()
        );

        //when
        Response response = client().target(getRestUri())
                .path("restaurant/orders")
                .path("confirm")
                .request()
                .post(Entity.json(restaurantOrderDetailsQuery));

        //then
        assertThat(response.getStatus()).isEqualTo(200);

        Order confirmedOrder = testOrdersDatabase.findById(saveNewOrder.getOrderId());

        assertThat(confirmedOrder).isNotNull();
        assertThat(confirmedOrder.getOrderStatus()).isEqualTo(OrderStatus.CONFIRMED);
    }

    @Test
    public void testPrepareOrder() throws URISyntaxException {
        //given
        Order saveConfirmedOrder = testOrdersDatabase.saveConfirmedOrder();

        PrepareOrderCommand restaurantOrderDetailsQuery = PrepareOrderCommand.of(
                saveConfirmedOrder.getOrderId()
        );

        //when
        Response response = client().target(getRestUri())
                .path("restaurant/orders")
                .path("prepare")
                .request()
                .post(Entity.json(restaurantOrderDetailsQuery));

        //then
        assertThat(response.getStatus()).isEqualTo(200);

        Order preparing = testOrdersDatabase.findById(saveConfirmedOrder.getOrderId());

        assertThat(preparing).isNotNull();
        assertThat(preparing.getOrderStatus()).isEqualTo(OrderStatus.PREPARING);
    }

    @Test
    public void testDoneOrder() throws URISyntaxException {
        //given
        Order savePreparingOrder = testOrdersDatabase.savePreparingOrder();

        PrepareOrderCommand restaurantOrderDetailsQuery = PrepareOrderCommand.of(
                savePreparingOrder.getOrderId()
        );

        //when
        Response response = client().target(getRestUri())
                .path("restaurant/orders")
                .path("done")
                .request()
                .post(Entity.json(restaurantOrderDetailsQuery));

        //then
        assertThat(response.getStatus()).isEqualTo(200);

        Order confirmedOrder = testOrdersDatabase.findById(savePreparingOrder.getOrderId());

        assertThat(confirmedOrder).isNotNull();
        assertThat(confirmedOrder.getOrderStatus()).isEqualTo(OrderStatus.DONE);
    }

    @Test
    public void testPayOrder() throws URISyntaxException {
        //given
        Order saveNewOrder = testOrdersDatabase.saveNewOrder();

        ConfirmOrderCommand restaurantOrderDetailsQuery = ConfirmOrderCommand.of(
                saveNewOrder.getOrderId()
        );

        //when
        Response response = client().target(getRestUri())
                .path("restaurant/orders")
                .path("pay")
                .request()
                .post(Entity.json(restaurantOrderDetailsQuery));

        //then
        assertThat(response.getStatus()).isEqualTo(200);

        Order confirmedOrder = testOrdersDatabase.findById(saveNewOrder.getOrderId());

        assertThat(confirmedOrder).isNotNull();
        assertThat(confirmedOrder.getOrderStatus()).isEqualTo(OrderStatus.PAID);
    }

    @Test
    public void testGivenOrderStatus() throws URISyntaxException {
        //given
        Order saveNewOrder = testOrdersDatabase.saveFinishOrder();

        ConfirmOrderCommand restaurantOrderDetailsQuery = ConfirmOrderCommand.of(
                saveNewOrder.getOrderId()
        );

        //when
        Response response = client().target(getRestUri())
                .path("restaurant/orders")
                .path("give")
                .request()
                .post(Entity.json(restaurantOrderDetailsQuery));

        //then
        assertThat(response.getStatus()).isEqualTo(200);

        Order confirmedOrder = testOrdersDatabase.findById(saveNewOrder.getOrderId());

        assertThat(confirmedOrder).isNotNull();
        assertThat(confirmedOrder.getOrderStatus()).isEqualTo(OrderStatus.GIVEN);
    }

    @Test
    public void testDeleteOrder() throws URISyntaxException {
        //given
        Order saveNewOrder = testOrdersDatabase.saveNewOrder();

        //when
        Response response = client().target(getRestUri())
                .path("restaurant/orders")
                .path("delete")
                .path(saveNewOrder.getOrderId().getId())
                .request()
                .delete();

        //then
        assertThat(response.getStatus()).isEqualTo(200);

        Order confirmedOrder = testOrdersDatabase.findById(saveNewOrder.getOrderId());

        assertThat(confirmedOrder).isNotNull();
        assertThat(confirmedOrder.getOrderStatus()).isEqualTo(OrderStatus.DELETED);
    }
}