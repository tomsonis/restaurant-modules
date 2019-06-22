package com.beben.tomasz.restaurant.user.infrastructure.spring.configuration;

import com.beben.tomasz.restaurant.commons.ContextHolder;
import com.beben.tomasz.restaurant.user.application.converters.ToRestaurantUserViewConverter;
import com.beben.tomasz.restaurant.user.application.converters.ToUserViewConverter;
import com.beben.tomasz.restaurant.user.application.query.LoggedUserDetailsQueryHandler;
import com.beben.tomasz.restaurant.user.application.query.RestaurantUserQueryHandler;
import com.beben.tomasz.restaurant.user.application.query.UserExistQueryHandler;
import com.beben.tomasz.restaurant.user.application.query.UserIdQueryHandler;
import com.beben.tomasz.restaurant.user.domain.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserQueryHandlersConfiguration {

    @Bean
    UserIdQueryHandler userIdQueryHandler(
            UserRepository userRepository
    ) {
        return new UserIdQueryHandler(
                userRepository
        );
    }

    @Bean
    LoggedUserDetailsQueryHandler userDetailsQueryHandler(
            UserRepository userRepository,
            ContextHolder contextHolder,
            ToUserViewConverter toUserViewConverter
    ) {
        return new LoggedUserDetailsQueryHandler(
                userRepository,
                contextHolder,
                toUserViewConverter
        );
    }

    @Bean
    RestaurantUserQueryHandler restaurantUserQueryHandler(
            UserRepository userRepository,
            ContextHolder contextHolder,
            ToRestaurantUserViewConverter toRestaurantUserViewConverter
    ) {
        return new RestaurantUserQueryHandler(
                userRepository,
                contextHolder,
                toRestaurantUserViewConverter
        );
    }

    @Bean
    UserExistQueryHandler userExistQueryHandler(
            UserRepository userRepository
    ) {
        return new UserExistQueryHandler(
                userRepository
        );
    }

}
