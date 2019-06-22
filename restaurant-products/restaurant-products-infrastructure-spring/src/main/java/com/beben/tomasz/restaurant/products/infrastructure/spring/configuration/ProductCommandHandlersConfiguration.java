package com.beben.tomasz.restaurant.products.infrastructure.spring.configuration;

import com.beben.tomasz.restaurant.products.application.command.AddProductCommandHandler;
import com.beben.tomasz.restaurant.products.domain.ProductFactory;
import com.beben.tomasz.restaurant.products.domain.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductCommandHandlersConfiguration {

    @Bean
    AddProductCommandHandler addProductCommandHandler(
            ProductRepository productRepository,
            ProductFactory productFactory
    ) {
        return new AddProductCommandHandler(
                productRepository,
                productFactory
        );
    }
}
