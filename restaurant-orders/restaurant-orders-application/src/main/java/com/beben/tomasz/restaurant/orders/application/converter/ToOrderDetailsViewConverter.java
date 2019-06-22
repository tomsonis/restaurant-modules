package com.beben.tomasz.restaurant.orders.application.converter;

import com.beben.tomasz.restaurant.commons.Converter;
import com.beben.tomasz.restaurant.core.api.query.restaurant.SearchRestaurantDetailsQuery;
import com.beben.tomasz.restaurant.core.api.query.tables.SearchTableDetailsViewQuery;
import com.beben.tomasz.restaurant.core.api.view.RestaurantTableView;
import com.beben.tomasz.restaurant.core.api.view.RestaurantView;
import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import com.beben.tomasz.restaurant.orders.api.OrderDetailsView;
import com.beben.tomasz.restaurant.orders.api.OrderPaymentView;
import com.beben.tomasz.restaurant.orders.api.OrderStatusView;
import com.beben.tomasz.restaurant.orders.api.PaymentTypeView;
import com.beben.tomasz.restaurant.orders.domain.order.Order;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class ToOrderDetailsViewConverter implements Converter<Order, OrderDetailsView> {

    private QueryExecutor queryExecutor;

    private ToOrderItemViewConverter toOrderItemViewConverter;

    private ToClientViewConverter toClientViewConverter;

    @Override
    public OrderDetailsView convert(Order order) {

        RestaurantTableView restaurantTableView = getRestaurantTableView(order);

        RestaurantView execute = getRestaurantView(order);
        return OrderDetailsView.of(
                order.getId(),
                toOrderItemViewConverter.convert(order.getOrderItemEntities()),
                OrderStatusView.valueOf(order.getOrderStatus().name()),
                OrderPaymentView.of(
                        order.getOrderPayment().getReferenceNumber(),
                        PaymentTypeView.valueOf(order.getOrderPayment().getPaymentType().name())
                ),
                execute,
                restaurantTableView,
                toClientViewConverter.convert(order.getClientData()),
                order.getTotalAmount(),
                order.getArrivalTime()
        );
    }

    private RestaurantView getRestaurantView(Order order) {
        try {
            SearchRestaurantDetailsQuery restaurantDetailsQuery = SearchRestaurantDetailsQuery.of(order.getRestaurantReference());
            return queryExecutor.execute(restaurantDetailsQuery);
        } catch (Exception e) {
            log.info("Error on execute query: {}", e.getMessage());
            return RestaurantView.newInstance();
        }
    }

    private RestaurantTableView getRestaurantTableView(Order order) {
        try {
            SearchTableDetailsViewQuery searchTableDetailsViewQuery = SearchTableDetailsViewQuery.of(order.getTableReference());
            return queryExecutor.execute(searchTableDetailsViewQuery);
        } catch (Exception e) {
            log.info("Error on execute query: {}", e.getMessage());
            return RestaurantTableView.newInstance();
        }
    }
}
