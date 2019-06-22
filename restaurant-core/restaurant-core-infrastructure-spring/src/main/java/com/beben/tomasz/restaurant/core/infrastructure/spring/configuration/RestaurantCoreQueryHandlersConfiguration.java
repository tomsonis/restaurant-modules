package com.beben.tomasz.restaurant.core.infrastructure.spring.configuration;

import com.beben.tomasz.restaurant.core.application.converters.ToRestaurantTableViewConverter;
import com.beben.tomasz.restaurant.core.application.converters.ToRestaurantViewConverter;
import com.beben.tomasz.restaurant.core.application.query.tables.ExistRestaurantAndTableQueryHandler;
import com.beben.tomasz.restaurant.core.application.query.restaurant.SearchByNameRestaurantQueryHandler;
import com.beben.tomasz.restaurant.core.application.query.restaurant.SearchRestaurantDetailsQueryHandler;
import com.beben.tomasz.restaurant.core.application.query.tables.SearchTableDetailsViewQueryHandler;
import com.beben.tomasz.restaurant.core.application.query.tables.SearchTablesByRestaurantQueryHandler;
import com.beben.tomasz.restaurant.core.domain.RestaurantRepository;
import com.beben.tomasz.restaurant.core.domain.RestaurantTableRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestaurantCoreQueryHandlersConfiguration {

    @Bean
    SearchByNameRestaurantQueryHandler searchByNameRestaurantQueryHandler(
            RestaurantRepository restaurantRepository,
            ToRestaurantViewConverter toRestaurantViewConverter
    ) {
        return new SearchByNameRestaurantQueryHandler(
                restaurantRepository,
                toRestaurantViewConverter
        );
    }

    @Bean
    SearchTablesByRestaurantQueryHandler searchTablesByRestaurantQueryHandler(
            RestaurantTableRepository restaurantTableRepository,
            ToRestaurantTableViewConverter toRestaurantTableViewConverter
    ) {
        return new SearchTablesByRestaurantQueryHandler(
                restaurantTableRepository,
                toRestaurantTableViewConverter
        );
    }

    @Bean
    SearchRestaurantDetailsQueryHandler searchRestaurantDetailsQueryHandler(
            RestaurantRepository restaurantRepository,
            ToRestaurantViewConverter toRestaurantViewConverter
    ) {
        return new SearchRestaurantDetailsQueryHandler(
                restaurantRepository,
                toRestaurantViewConverter
        );
    }

    @Bean
    SearchTableDetailsViewQueryHandler searchTableDetailsViewQueryHandler(
            RestaurantTableRepository restaurantTableRepository,
            ToRestaurantTableViewConverter toRestaurantTableViewConverter
    ) {
        return new SearchTableDetailsViewQueryHandler(
                restaurantTableRepository,
                toRestaurantTableViewConverter
        );
    }

    @Bean
    ExistRestaurantAndTableQueryHandler existRestaurantAndTableQueryHandler(
            RestaurantRepository restaurantRepository,
            RestaurantTableRepository restaurantTableRepository
    ) {
        return new ExistRestaurantAndTableQueryHandler(
                restaurantRepository,
                restaurantTableRepository
        );
    }
}
