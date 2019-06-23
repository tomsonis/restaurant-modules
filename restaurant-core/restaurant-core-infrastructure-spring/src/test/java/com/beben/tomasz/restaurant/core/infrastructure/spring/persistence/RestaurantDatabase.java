package com.beben.tomasz.restaurant.core.infrastructure.spring.persistence;

import com.beben.tomasz.restaurant.core.domain.Restaurant;
import com.beben.tomasz.restaurant.core.domain.RestaurantId;
import com.beben.tomasz.restaurant.core.domain.RestaurantTable;
import com.beben.tomasz.restaurant.core.domain.TableId;
import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.UUID;

@AllArgsConstructor
@Transactional
public class RestaurantDatabase {

    private EntityManager entityManager;

    public void clearRestaurantEntity() {
        entityManager.createQuery("DELETE FROM RestaurantEntity").executeUpdate();
    }

    public void clearRestaurantTableEntity() {
        entityManager.createQuery("DELETE FROM RestaurantTableEntity").executeUpdate();
    }

    public Restaurant saveRestaurantEntity() {
        Restaurant restaurant = createRestaurant();

        entityManager.persist(restaurant);

        return restaurant;
    }

    public RestaurantTable saveRestaurantTableEntity() {
        Restaurant restaurant = saveRestaurantEntity();
        RestaurantTable restaurantTable = createRestaurantTable(restaurant.getRestaurantId());

        entityManager.persist(restaurantTable);

        return restaurantTable;
    }

    public RestaurantTable findTableById(TableId tableId) {
        return entityManager.find(RestaurantTableEntity.class, tableId.getId());

    }

    public Restaurant findRestaurantById(RestaurantId restaurantId) {
        return entityManager.find(RestaurantEntity.class, restaurantId.getId());
    }

    private RestaurantTable createRestaurantTable(RestaurantId restaurantId) {
        return RestaurantTableEntity.of(
                UUID.randomUUID().toString(),
                "TEST_RESTAURANT_TABLE_NAME",
                "TEST_POSITION",
                restaurantId.getId(),
                1
        );
    }

    private Restaurant createRestaurant() {
        return RestaurantEntity.of(
                UUID.randomUUID().toString(),
                "TEST_RESRAURANT_NAME",
                "TEST_DESCRIPTION",
                RestaurantAddress.of(
                        "TEST_STREET",
                        "TEST_POSTAL_CODE",
                        "TEST_CITY",
                        "TEST_COUNTRY"
                ),
                "TEST_PHOTO_URL"
        );
    }
}
