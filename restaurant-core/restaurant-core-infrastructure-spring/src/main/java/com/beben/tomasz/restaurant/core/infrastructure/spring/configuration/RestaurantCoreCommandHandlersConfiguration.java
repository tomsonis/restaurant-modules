package com.beben.tomasz.restaurant.core.infrastructure.spring.configuration;

import com.beben.tomasz.restaurant.core.application.command.AddRestaurantCommandHandler;
import com.beben.tomasz.restaurant.core.application.command.AddTableCommandHandler;
import com.beben.tomasz.restaurant.core.application.converters.ToRestaurantAddressConverter;
import com.beben.tomasz.restaurant.core.application.converters.ToRestaurantViewConverter;
import com.beben.tomasz.restaurant.core.application.query.restaurant.SearchRestaurantsQueryHandler;
import com.beben.tomasz.restaurant.core.domain.RestaurantFactory;
import com.beben.tomasz.restaurant.core.domain.RestaurantRepository;
import com.beben.tomasz.restaurant.core.domain.RestaurantTableFactory;
import com.beben.tomasz.restaurant.core.domain.RestaurantTableRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestaurantCoreCommandHandlersConfiguration {

    @Bean
    AddTableCommandHandler addTableCommandHandler(
            RestaurantTableRepository restaurantTableRepository,
            RestaurantTableFactory restaurantTableFactory
    ) {
        return new AddTableCommandHandler(
                restaurantTableRepository,
                restaurantTableFactory
        );
    }

    @Bean
    AddRestaurantCommandHandler addRestaurantCommandHandler(
            RestaurantRepository restaurantRepository,
            RestaurantFactory restaurantFactory,
            ToRestaurantAddressConverter toRestaurantAddressConverter
    ) {
        return new AddRestaurantCommandHandler(
                restaurantRepository,
                restaurantFactory,
                toRestaurantAddressConverter
        );
    }

    @Bean
    SearchRestaurantsQueryHandler searchRestaurantsQueryHandler(
            RestaurantRepository restaurantRepository,
            ToRestaurantViewConverter toRestaurantViewConverter
    ) {
        return new SearchRestaurantsQueryHandler(
                restaurantRepository,
                toRestaurantViewConverter
        );
    }
}
