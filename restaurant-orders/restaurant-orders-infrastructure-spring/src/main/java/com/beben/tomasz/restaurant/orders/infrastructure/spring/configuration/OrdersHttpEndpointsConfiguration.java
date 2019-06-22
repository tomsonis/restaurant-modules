package com.beben.tomasz.restaurant.orders.infrastructure.spring.configuration;

import com.beben.tomasz.cqrs.api.command.CommandExecutor;
import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import com.beben.tomasz.restaurant.orders.infrastructure.spring.delivery.OrdersHttpEndpoint;
import com.beben.tomasz.restaurant.orders.infrastructure.spring.delivery.RestaurantOrdersHttpEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrdersHttpEndpointsConfiguration {

    @Bean
    OrdersHttpEndpoint ordersHttpEndpoint(
            CommandExecutor commandExecutor,
            QueryExecutor queryExecutor
    ) {
        return new OrdersHttpEndpoint(
                commandExecutor,
                queryExecutor
        );
    }

    @Bean
    RestaurantOrdersHttpEndpoint restaurantOrdersHttpEndpoint(
            CommandExecutor commandExecutor,
            QueryExecutor queryExecutor
    ) {
        return new RestaurantOrdersHttpEndpoint(
                commandExecutor,
                queryExecutor
        );
    }
}
