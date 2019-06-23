package com.beben.tomasz.restaurant.user.infrastructure.spring.persistance.user;

import com.beben.tomasz.restaurant.user.domain.RestaurantRoleType;
import com.beben.tomasz.restaurant.user.domain.RestaurantUser;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import java.util.Collection;


@Getter
@Table
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class RestaurantUserEntity implements RestaurantUser {

    @Id
    private String id;

    private String restaurantReference;

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(
            name = "restaurant_user_roles",
            joinColumns = @JoinColumn(name = "restaurant_user_id")
    )
    @Column(name = "restaurant_role_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private Collection<RestaurantRoleType> restaurantRoleTypes;
}
