package com.beben.tomasz.restaurant.user.infrastructure.spring.persistance.client;

import com.beben.tomasz.restaurant.user.domain.ClientId;
import com.beben.tomasz.restaurant.user.domain.ClientRepository;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class JpaClientRepository implements ClientRepository {

    @Override
    public ClientId generateId() {
        return ClientId.of(
                "RCL-" + UUID.randomUUID().toString()
        );
    }
}
