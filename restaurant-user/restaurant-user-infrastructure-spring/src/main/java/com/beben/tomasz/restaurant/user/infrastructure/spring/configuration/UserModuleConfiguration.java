package com.beben.tomasz.restaurant.user.infrastructure.spring.configuration;

import com.beben.tomasz.restaurant.user.application.converters.ToRestaurantUserViewConverter;
import com.beben.tomasz.restaurant.user.application.converters.ToUserViewConverter;
import com.beben.tomasz.restaurant.user.domain.UserRepository;
import com.beben.tomasz.restaurant.user.infrastructure.spring.security.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@Import({
        UserDomainConfiguration.class,
        UserCommandHandlersConfiguration.class,
        UserHttpEndpointsConfiguration.class,
        UserQueryHandlersConfiguration.class
})
public class UserModuleConfiguration {

    @Bean
    UserDetailsService userDetailsService(
            UserRepository userRepository
    ) {
        return new UserDetailsServiceImpl(
                userRepository
        );
    }

    @Bean
    ToUserViewConverter toUserViewConverter() {
        return new ToUserViewConverter();
    }

    @Bean
    ToRestaurantUserViewConverter toRestaurantUserViewConverter() {
        return new ToRestaurantUserViewConverter();
    }

}
