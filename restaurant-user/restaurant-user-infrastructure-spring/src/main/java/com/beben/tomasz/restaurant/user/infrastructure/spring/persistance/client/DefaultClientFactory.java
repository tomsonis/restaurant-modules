package com.beben.tomasz.restaurant.user.infrastructure.spring.persistance.client;

import com.beben.tomasz.restaurant.user.domain.ClientFactory;
import com.beben.tomasz.restaurant.user.domain.ClientRepository;
import com.beben.tomasz.restaurant.user.domain.RestaurantClient;
import com.beben.tomasz.restaurant.user.infrastructure.spring.persistance.ClientEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DefaultClientFactory implements ClientFactory {

    private ClientRepository clientRepository;

    @Override
    public RestaurantClient createClient(String name, String surname, String phoneNumber) {
        return ClientEntity.of(
                clientRepository.generateId().getId(),
                name,
                surname,
                phoneNumber
        );
    }
}
