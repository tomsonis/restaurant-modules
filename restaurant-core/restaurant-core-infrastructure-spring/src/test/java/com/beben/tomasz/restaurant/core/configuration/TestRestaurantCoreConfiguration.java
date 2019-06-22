package com.beben.tomasz.restaurant.core.configuration;

import com.beben.tomasz.restaurant.core.infrastructure.spring.persistence.RestaurantDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class TestRestaurantCoreConfiguration {

    @Bean
    RestaurantDatabase restaurantTableCleaner(EntityManager entityManager) {
        return new RestaurantDatabase(entityManager);
    }

}
