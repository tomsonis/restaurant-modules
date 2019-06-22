package com.beben.tomasz.restaurant.core.infrastructure.spring.persistence;

import com.beben.tomasz.restaurant.core.domain.Restaurant;
import com.beben.tomasz.restaurant.core.domain.RestaurantTable;
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
        RestaurantTable restaurantTable = createRestaurantTable(restaurant.getId());

        entityManager.persist(restaurantTable);

        return restaurantTable;
    }

    public RestaurantTable findTableById(String id) {
        return entityManager.find(RestaurantTableEntity.class, id);

    }

    public Restaurant findRestaurantById(String id) {
        return entityManager.find(RestaurantEntity.class, id);
    }

    private RestaurantTable createRestaurantTable(String restaurantId) {
        return RestaurantTableEntity.of(
                UUID.randomUUID().toString(),
                "TEST_RESTAURANT_TABLE_NAME",
                "TEST_POSITION",
                restaurantId,
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
