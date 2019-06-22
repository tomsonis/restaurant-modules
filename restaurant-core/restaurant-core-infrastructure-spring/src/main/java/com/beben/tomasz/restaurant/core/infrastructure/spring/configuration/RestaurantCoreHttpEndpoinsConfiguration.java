package com.beben.tomasz.restaurant.core.infrastructure.spring.configuration;

import com.beben.tomasz.restaurant.core.infrastructure.spring.delivery.RestaurantHttpEndpoint;
import com.beben.tomasz.restaurant.core.infrastructure.spring.delivery.RestaurantTableHttpEndpoint;
import com.beben.tomasz.cqrs.api.command.CommandExecutor;
import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestaurantCoreHttpEndpoinsConfiguration {

    @Bean
    RestaurantHttpEndpoint restaurantHttpEndpointProducer(
            CommandExecutor commandExecutor,
            QueryExecutor queryExecutor
    ) {
        return new RestaurantHttpEndpoint(
                commandExecutor,
                queryExecutor
        );
    }

    @Bean
    RestaurantTableHttpEndpoint restaurantTableHttpEndpoint(
            CommandExecutor commandExecutor,
            QueryExecutor queryExecutor
    ) {
        return new RestaurantTableHttpEndpoint(
                commandExecutor,
                queryExecutor
        );
    }
}
