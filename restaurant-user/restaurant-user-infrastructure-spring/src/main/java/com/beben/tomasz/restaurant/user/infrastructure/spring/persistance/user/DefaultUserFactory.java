package com.beben.tomasz.restaurant.user.infrastructure.spring.persistance.user;

import com.beben.tomasz.restaurant.user.domain.ApplicationUser;
import com.beben.tomasz.restaurant.user.domain.RestaurantClient;
import com.beben.tomasz.restaurant.user.domain.RestaurantUser;
import com.beben.tomasz.restaurant.user.domain.UserFactory;
import com.beben.tomasz.restaurant.user.domain.UserRepository;
import com.beben.tomasz.restaurant.user.infrastructure.spring.persistance.ClientEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@AllArgsConstructor
public class DefaultUserFactory implements UserFactory {

    private BCryptPasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    @Override
    public ApplicationUser createUser(
            String username,
            String password,
            String email,
            RestaurantClient restaurantClient,
            RestaurantUser restaurantUser
    ) {

        return new UserEntity(
                userRepository.generateId().getId(),
                username,
                passwordEncoder.encode(password),
                email,
                (ClientEntity) restaurantClient,
                (RestaurantUserEntity) restaurantUser
        );
    }
}
