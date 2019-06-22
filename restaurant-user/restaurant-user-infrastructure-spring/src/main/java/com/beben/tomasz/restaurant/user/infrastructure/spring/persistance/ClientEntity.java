package com.beben.tomasz.restaurant.user.infrastructure.spring.persistance;

import com.beben.tomasz.restaurant.user.domain.ClientId;
import com.beben.tomasz.restaurant.user.domain.RestaurantClient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
@Getter
@NoArgsConstructor(staticName = "newInstance")
@AllArgsConstructor(staticName = "of")
public class ClientEntity implements RestaurantClient {

    @Id
    private String id;

    private String name;

    private String surname;

    private String phoneNumber;

    @Override
    public ClientId getClientId() {
        return ClientId.of(id);
    }
}
