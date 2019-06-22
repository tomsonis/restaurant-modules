package com.beben.tomasz.restaurant.core.infrastructure.spring.configuration;

import com.beben.tomasz.restaurant.core.domain.RestaurantRepository;
import com.beben.tomasz.restaurant.core.domain.RestaurantTableRepository;
import com.beben.tomasz.restaurant.core.infrastructure.spring.persistence.read.JpaRestaurantRepository;
import com.beben.tomasz.restaurant.core.infrastructure.spring.persistence.read.JpaRestaurantTableRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class RestaurantCoreRepositoriesConfiguration {

    @Bean
    RestaurantTableRepository jpaRestaurantTableRepository(
            EntityManager entityManager
    ) {
        return new JpaRestaurantTableRepository(
                entityManager
        );
    }

    @Bean
    RestaurantRepository restaurantRepository(
            EntityManager entityManager
    ) {
        return new JpaRestaurantRepository(
                entityManager
        );
    }
}
