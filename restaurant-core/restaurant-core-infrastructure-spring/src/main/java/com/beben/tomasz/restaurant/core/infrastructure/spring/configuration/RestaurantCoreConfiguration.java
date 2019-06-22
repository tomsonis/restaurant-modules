package com.beben.tomasz.restaurant.core.infrastructure.spring.configuration;

import com.beben.tomasz.restaurant.core.application.converters.ToRestaurantAddressConverter;
import com.beben.tomasz.restaurant.core.application.converters.ToRestaurantTableViewConverter;
import com.beben.tomasz.restaurant.core.application.converters.ToRestaurantViewConverter;
import com.beben.tomasz.restaurant.core.domain.RestaurantFactory;
import com.beben.tomasz.restaurant.core.domain.RestaurantRepository;
import com.beben.tomasz.restaurant.core.domain.RestaurantTableFactory;
import com.beben.tomasz.restaurant.core.domain.RestaurantTableRepository;
import com.beben.tomasz.restaurant.core.infrastructure.spring.persistence.DefaultRestaurantFactory;
import com.beben.tomasz.restaurant.core.infrastructure.spring.persistence.DefaultRestaurantTableFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        RestaurantCoreHttpEndpoinsConfiguration.class,
        RestaurantCoreRepositoriesConfiguration.class,
        RestaurantCoreCommandHandlersConfiguration.class,
        RestaurantCoreQueryHandlersConfiguration.class

})
public class RestaurantCoreConfiguration {

    @Bean
    RestaurantTableFactory restaurantTableFactory(
            RestaurantTableRepository restaurantTableRepository,
            RestaurantRepository restaurantRepository
    ) {
        return new DefaultRestaurantTableFactory(
                restaurantTableRepository,
                restaurantRepository
        );
    }

    @Bean
    RestaurantFactory restaurantFactory(
            RestaurantRepository restaurantRepository
    ) {
        return new DefaultRestaurantFactory(
                restaurantRepository
        );
    }

    @Bean
    ToRestaurantViewConverter toRestaurantViewConverter() {
        return new ToRestaurantViewConverter();
    }

    @Bean
    ToRestaurantTableViewConverter toRestaurantTableViewConverter() {
        return new ToRestaurantTableViewConverter();
    }

    @Bean
    ToRestaurantAddressConverter toAddressConverter() {
        return new ToRestaurantAddressConverter();
    }
}
