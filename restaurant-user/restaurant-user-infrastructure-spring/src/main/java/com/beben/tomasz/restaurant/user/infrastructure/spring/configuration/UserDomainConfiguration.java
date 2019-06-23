package com.beben.tomasz.restaurant.user.infrastructure.spring.configuration;

import com.beben.tomasz.restaurant.user.domain.ClientFactory;
import com.beben.tomasz.restaurant.user.domain.ClientRepository;
import com.beben.tomasz.restaurant.user.domain.RestaurantUserFactory;
import com.beben.tomasz.restaurant.user.domain.RestaurantUserRepository;
import com.beben.tomasz.restaurant.user.domain.UserFactory;
import com.beben.tomasz.restaurant.user.domain.UserRepository;
import com.beben.tomasz.restaurant.user.infrastructure.spring.persistance.client.DefaultClientFactory;
import com.beben.tomasz.restaurant.user.infrastructure.spring.persistance.client.JpaClientRepository;
import com.beben.tomasz.restaurant.user.infrastructure.spring.persistance.user.DefaultRestaurantUserFactory;
import com.beben.tomasz.restaurant.user.infrastructure.spring.persistance.user.DefaultUserFactory;
import com.beben.tomasz.restaurant.user.infrastructure.spring.persistance.user.JpaRestaurantUserRepository;
import com.beben.tomasz.restaurant.user.infrastructure.spring.persistance.user.JpaUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.EntityManager;

@Configuration
public class UserDomainConfiguration {

    @Bean
    UserFactory userFactory(
            BCryptPasswordEncoder bCryptPasswordEncoder,
            UserRepository userRepository
    ) {
        return new DefaultUserFactory(
                bCryptPasswordEncoder,
                userRepository
        );
    }

    @Bean
    UserRepository userRepository(
            EntityManager entityManager
    ) {
        return new JpaUserRepository(
                entityManager
        );
    }

    @Bean
    ClientFactory clientFactory(
            ClientRepository clientRepository
    ) {
        return new DefaultClientFactory(
                clientRepository
        );
    }
    @Bean
    ClientRepository clientRepository() {
        return new JpaClientRepository();
    }

    @Bean
    RestaurantUserRepository restaurantUserRepository() {
        return new JpaRestaurantUserRepository();
    }

    @Bean
    RestaurantUserFactory restaurantUserFactory(
            RestaurantUserRepository restaurantUserRepository
    ) {
        return new DefaultRestaurantUserFactory(
                restaurantUserRepository
        );
    }
}
