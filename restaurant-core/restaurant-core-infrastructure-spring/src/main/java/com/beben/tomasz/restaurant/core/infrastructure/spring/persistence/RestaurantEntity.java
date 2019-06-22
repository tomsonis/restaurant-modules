package com.beben.tomasz.restaurant.core.infrastructure.spring.persistence;

import com.beben.tomasz.restaurant.core.domain.Address;
import com.beben.tomasz.restaurant.core.domain.Restaurant;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Table
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class RestaurantEntity implements Restaurant {

    @Id
    private String id;

    private String name;

    private String description;

    @Embedded
    private RestaurantAddress restaurantAddress;

    private String photoUrl;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    private RestaurantEntity(String id, String name, String description, RestaurantAddress restaurantAddress, String photoUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.restaurantAddress = restaurantAddress;
        this.photoUrl = photoUrl;
    }

    public static RestaurantEntity of(String id, String name, String description, RestaurantAddress restaurantAddress, String photoUrl) {
        return new RestaurantEntity(id, name, description, restaurantAddress, photoUrl);
    }

    @Override
    public Address getAddress() {
        return Address.of(
                restaurantAddress.getStreet(),
                restaurantAddress.getPostalCode(),
                restaurantAddress.getCity(),
                restaurantAddress.getCountry()
        );
    }
}
