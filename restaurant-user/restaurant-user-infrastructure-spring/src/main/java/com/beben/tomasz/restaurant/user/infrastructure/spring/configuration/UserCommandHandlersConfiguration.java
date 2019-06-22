package com.beben.tomasz.restaurant.user.infrastructure.spring.configuration;

import com.beben.tomasz.restaurant.user.application.command.RegisterUserCommandHandler;
import com.beben.tomasz.restaurant.user.application.command.SaveTokenCommandHandler;
import com.beben.tomasz.restaurant.user.domain.ClientFactory;
import com.beben.tomasz.restaurant.user.domain.RestaurantUserFactory;
import com.beben.tomasz.restaurant.user.domain.UserFactory;
import com.beben.tomasz.restaurant.user.domain.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserCommandHandlersConfiguration {

    @Bean
    RegisterUserCommandHandler registerUserCommandHandler(
            UserRepository userRepository,
            UserFactory userFactory,
            ClientFactory clientFactory,
            RestaurantUserFactory restaurantUserFactory
    ) {
        return new RegisterUserCommandHandler(
                userRepository,
                userFactory,
                clientFactory,
                restaurantUserFactory
        );
    }

    @Bean
    SaveTokenCommandHandler saveTokenCommandHandler(
            UserRepository userRepository
    ) {
        return new SaveTokenCommandHandler(
                userRepository
        );
    }
}
