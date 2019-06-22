package com.beben.tomasz.restaurant.core.infrastructure.spring.persistence.read;

import com.beben.tomasz.restaurant.core.domain.RestaurantTable;
import com.beben.tomasz.restaurant.core.domain.RestaurantTableRepository;
import com.beben.tomasz.restaurant.core.domain.TableId;
import com.beben.tomasz.restaurant.core.infrastructure.spring.persistence.RestaurantTableEntity;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@Transactional
@AllArgsConstructor
public class JpaRestaurantTableRepository implements RestaurantTableRepository {

    private EntityManager entityManager;

    @Override
    public TableId generateId() {
        return TableId.of(
                UUID.randomUUID().toString()
        );
    }

    @Override
    public String save(RestaurantTable restaurantTable) {
        entityManager.persist(restaurantTable);
        return restaurantTable.getId();
    }

    @Override
    public List<RestaurantTable> findByRestaurantAndDistinctCapacity(String restaurantReference) {
        return entityManager.createQuery(
                        "select r from RestaurantTableEntity r where r.restaurantReference = :restaurantReference ORDER BY r.capacity ASC",
                        RestaurantTable.class
                )
                .setParameter("restaurantReference", restaurantReference)
                .getResultList();
    }

    @Override
    public RestaurantTable find(String id) {
        return entityManager.find(RestaurantTableEntity.class, id);
    }

    @Override
    public boolean exists(String tableId) {
        List<RestaurantTable> restaurantTableList = entityManager.createQuery(
                        "select r from RestaurantTableEntity r where r.id = :id",
                        RestaurantTable.class
                )
                .setParameter("id", tableId)
                .getResultList();

        return !restaurantTableList.isEmpty();
    }
}
