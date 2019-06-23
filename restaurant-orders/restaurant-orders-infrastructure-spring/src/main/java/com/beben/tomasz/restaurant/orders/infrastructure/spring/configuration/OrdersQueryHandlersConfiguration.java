package com.beben.tomasz.restaurant.orders.infrastructure.spring.configuration;

import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import com.beben.tomasz.restaurant.commons.ContextHolder;
import com.beben.tomasz.restaurant.orders.application.converter.ToOrderDetailsViewConverter;
import com.beben.tomasz.restaurant.orders.application.query.OrderReadRepository;
import com.beben.tomasz.restaurant.orders.application.query.restaurant.RestaurantOrderDetailsQueryHandler;
import com.beben.tomasz.restaurant.orders.application.query.restaurant.RestaurantOrderListQueryHandler;
import com.beben.tomasz.restaurant.orders.application.query.restaurant.SearchOrderToTrackQueryHandler;
import com.beben.tomasz.restaurant.orders.application.query.user.details.OrderDetailsQueryHandler;
import com.beben.tomasz.restaurant.orders.application.query.user.list.OrderListQueryHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OrdersQueryHandlersConfiguration {

    @Bean
    OrderDetailsQueryHandler orderDetailsQueryHandler(
            OrderReadRepository orderReadRepository,
            ToOrderDetailsViewConverter toOrderDetailsViewConverter,
            ContextHolder contextHolder
    ) {
        return new OrderDetailsQueryHandler(
                orderReadRepository,
                toOrderDetailsViewConverter,
                contextHolder
        );
    }

    @Bean
    OrderListQueryHandler orderListQueryHandler(
            OrderReadRepository orderReadRepository,
            ToOrderDetailsViewConverter toOrderDetailsViewConverter,
            ContextHolder contextHolder
    ) {
        return new OrderListQueryHandler(
                orderReadRepository,
                toOrderDetailsViewConverter,
                contextHolder
        );
    }

    @Bean
    SearchOrderToTrackQueryHandler searchOrderToTrackQueryHandler(
            OrderReadRepository orderReadRepository,
            ToOrderDetailsViewConverter toOrderDetailsViewConverter,
            QueryExecutor queryExecutor
    ) {
        return new SearchOrderToTrackQueryHandler(
                orderReadRepository,
                toOrderDetailsViewConverter,
                queryExecutor
        );
    }

    @Bean
    RestaurantOrderDetailsQueryHandler restaurantOrderDetailsQueryHandler(
            OrderReadRepository orderReadRepository,
            ToOrderDetailsViewConverter toOrderDetailsViewConverter,
            QueryExecutor queryExecutor
    ) {
        return new RestaurantOrderDetailsQueryHandler(
                orderReadRepository,
                toOrderDetailsViewConverter,
                queryExecutor
        );
    }

    @Bean
    RestaurantOrderListQueryHandler restaurantOrderListQueryHandler(
            OrderReadRepository orderReadRepository,
            ToOrderDetailsViewConverter toOrderDetailsViewConverter,
            QueryExecutor queryExecutor

    ) {
        return new RestaurantOrderListQueryHandler(
                orderReadRepository,
                toOrderDetailsViewConverter,
                queryExecutor
        );
    }
}
