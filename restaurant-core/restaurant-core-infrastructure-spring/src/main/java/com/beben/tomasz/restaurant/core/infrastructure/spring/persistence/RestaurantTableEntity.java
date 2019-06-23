package com.beben.tomasz.restaurant.core.infrastructure.spring.persistence;

import com.beben.tomasz.restaurant.core.domain.RestaurantId;
import com.beben.tomasz.restaurant.core.domain.RestaurantTable;
import com.beben.tomasz.restaurant.core.domain.TableId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class RestaurantTableEntity implements RestaurantTable {

    @Id
    private String id;

    @Getter
    private String name;

    @Getter
    private String position;

    private String restaurantReference;

    @Getter
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

    @Override
    public TableId getTableId() {
        return TableId.of(id);
    }

    @Override
    public RestaurantId getRestaurantReference() {
        return RestaurantId.of(restaurantReference);
    }
}
