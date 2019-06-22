package com.beben.tomasz.restaurant.products.infrastructure.spring.configuration;

import com.beben.tomasz.cqrs.api.command.CommandExecutor;
import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import com.beben.tomasz.restaurant.products.application.query.ProductsReadRepository;
import com.beben.tomasz.restaurant.products.infrastructure.spring.delivery.ProductsHttpEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductsHttpEndpointsConfiguration {

    @Bean
    ProductsHttpEndpoint productsHttpEndpoint(
            CommandExecutor commandExecutor,
            QueryExecutor queryExecutor,
            ProductsReadRepository productsReadRepository
    ) {
        return new ProductsHttpEndpoint(
                commandExecutor,
                queryExecutor,
                productsReadRepository
        );
    }

}
