package com.beben.tomasz.restaurant.core.infrastructure.spring.persistence;

import com.beben.tomasz.restaurant.core.domain.Address;
import com.beben.tomasz.restaurant.core.domain.Restaurant;
import com.beben.tomasz.restaurant.core.domain.RestaurantId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class RestaurantEntity implements Restaurant {

    @Id
    private String id;

    @Getter
    private String name;

    @Getter
    private String description;

    @Embedded
    private RestaurantAddress restaurantAddress;

    @Getter
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
    public RestaurantId getRestaurantId() {
        return RestaurantId.of(id);
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
