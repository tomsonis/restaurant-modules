package com.beben.tomasz.restaurant.core.infrastructure.spring.persistence;

import com.beben.tomasz.restaurant.core.domain.RestaurantTable;
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
public class RestaurantTableEntity implements RestaurantTable {

    @Id
    private String id;

    private String name;

    private String position;

    private String restaurantReference;

    private int capacity;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    private RestaurantTableEntity(String id, String name, String position, String restaurantReference, int capacity) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.restaurantReference = restaurantReference;
        this.capacity = capacity;
    }

    public static RestaurantTableEntity of(String id, String name, String position, String restaurantReference, int capacity) {
        return new RestaurantTableEntity(id, name, position, restaurantReference, capacity);
    }
}
