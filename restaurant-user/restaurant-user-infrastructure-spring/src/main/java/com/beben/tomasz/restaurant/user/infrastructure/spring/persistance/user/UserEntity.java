package com.beben.tomasz.restaurant.user.infrastructure.spring.persistance.user;

import com.beben.tomasz.restaurant.user.domain.AuthenticateUser;
import com.beben.tomasz.restaurant.user.domain.RestaurantClient;
import com.beben.tomasz.restaurant.user.domain.RestaurantUser;
import com.beben.tomasz.restaurant.user.domain.UserId;
import com.beben.tomasz.restaurant.user.infrastructure.spring.persistance.ClientEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table
@Entity
@NoArgsConstructor(staticName = "newInstance")
@AllArgsConstructor(staticName = "of")
@EntityListeners(AuditingEntityListener.class)
public class UserEntity implements AuthenticateUser  {

    @Id
    private String id;

    @Getter
    private String username;

    @Getter
    private String password;

    @Getter
    private String email;

    private String token;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private ClientEntity clientEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_user_id")
    private RestaurantUserEntity restaurantUser;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public UserEntity(
            String id,
            String username,
            String password,
            String email,
            ClientEntity clientEntity,
            RestaurantUserEntity restaurantUser
    ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.clientEntity = clientEntity;
        this.restaurantUser = restaurantUser;
    }

    @Override
    public UserId getUserId() {
        return UserId.of(id);
    }

    @Override
    public RestaurantClient getRestaurantClient() {
        return clientEntity;
    }

    @JsonIgnore
    @Override
    public String getToken() {
        return token;
    }

    @Override
    public RestaurantUser getRestaurantUser() {
        return restaurantUser;
    }

    @Override
    public void authenticate(String token) {
        this.token = token;
    }
}
