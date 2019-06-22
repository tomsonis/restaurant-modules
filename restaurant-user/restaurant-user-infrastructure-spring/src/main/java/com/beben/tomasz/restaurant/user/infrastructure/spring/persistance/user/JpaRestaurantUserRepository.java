package com.beben.tomasz.restaurant.user.infrastructure.spring.persistance.user;

import com.beben.tomasz.restaurant.user.domain.RestaurantUserId;
import com.beben.tomasz.restaurant.user.domain.RestaurantUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
@AllArgsConstructor
public class JpaRestaurantUserRepository implements RestaurantUserRepository {

    @Override
    public RestaurantUserId generateId() {
        return RestaurantUserId.of(
                UUID.randomUUID().toString()
        );
    }
}
