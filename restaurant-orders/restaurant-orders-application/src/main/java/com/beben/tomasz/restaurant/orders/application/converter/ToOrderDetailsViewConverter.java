package com.beben.tomasz.restaurant.orders.application.converter;

import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import com.beben.tomasz.restaurant.commons.Converter;
import com.beben.tomasz.restaurant.core.api.query.restaurant.SearchRestaurantDetailsQuery;
import com.beben.tomasz.restaurant.core.api.query.tables.SearchTableDetailsViewQuery;
import com.beben.tomasz.restaurant.core.api.view.RestaurantTableView;
import com.beben.tomasz.restaurant.core.api.view.RestaurantView;
import com.beben.tomasz.restaurant.core.domain.RestaurantId;
import com.beben.tomasz.restaurant.orders.api.OrderDetailsView;
import com.beben.tomasz.restaurant.orders.api.OrderPaymentView;
import com.beben.tomasz.restaurant.orders.api.OrderStatusView;
import com.beben.tomasz.restaurant.orders.api.PaymentTypeView;
import com.beben.tomasz.restaurant.orders.domain.order.Order;
import com.beben.tomasz.restaurant.orders.domain.order.TableId;
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

        RestaurantTableView restaurantTableView = getRestaurantTableView(order.getTableReference());

        RestaurantView execute = getRestaurantView(order.getRestaurantReference());
        return OrderDetailsView.of(
                order.getOrderId().getId(),
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

    private RestaurantView getRestaurantView(com.beben.tomasz.restaurant.orders.domain.order.RestaurantId restaurantId) {
        try {
            SearchRestaurantDetailsQuery restaurantDetailsQuery = SearchRestaurantDetailsQuery.of(
                    RestaurantId.of(restaurantId.getId())
            );
            return queryExecutor.execute(restaurantDetailsQuery);
        } catch (Exception e) {
            log.info("Error on execute query: {}", e.getMessage());
            return RestaurantView.newInstance();
        }
    }

    private RestaurantTableView getRestaurantTableView(TableId tableId) {
        try {
            SearchTableDetailsViewQuery searchTableDetailsViewQuery = SearchTableDetailsViewQuery.of(
                    com.beben.tomasz.restaurant.core.domain.TableId.of(tableId.getId())
            );
            return queryExecutor.execute(searchTableDetailsViewQuery);
        } catch (Exception e) {
            log.info("Error on execute query: {}", e.getMessage());
            return RestaurantTableView.newInstance();
        }
    }
}
