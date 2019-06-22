package com.beben.tomasz.restaurant.products.configuration;

import com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.ProductsDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class TestProductsConfiguration {

    @Bean
    ProductsDatabase restaurantTableCleaner(EntityManager entityManager) {
        return new ProductsDatabase(entityManager);
    }

}
