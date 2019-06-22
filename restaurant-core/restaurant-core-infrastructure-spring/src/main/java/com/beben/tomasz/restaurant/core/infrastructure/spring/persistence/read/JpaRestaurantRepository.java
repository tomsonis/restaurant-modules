package com.beben.tomasz.restaurant.core.infrastructure.spring.persistence.read;

import com.beben.tomasz.restaurant.core.domain.Restaurant;
import com.beben.tomasz.restaurant.core.domain.RestaurantId;
import com.beben.tomasz.restaurant.core.domain.RestaurantNotExistException;
import com.beben.tomasz.restaurant.core.domain.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@Transactional
@AllArgsConstructor
public class JpaRestaurantRepository implements RestaurantRepository {

    private EntityManager entityManager;

    @Override
    public String save(Restaurant restaurant) {

        entityManager.merge(restaurant);

        return restaurant.getId();
    }

    @Override
    public Restaurant findById(String id) throws RestaurantNotExistException {
        List<Restaurant> resultList = entityManager.createQuery(
                "select r from RestaurantEntity r where r.id = :id",
                Restaurant.class
        )
                .setParameter("id", id)
                .getResultList();

        if (resultList.isEmpty()) {
            throw new RestaurantNotExistException(String.format("Restaurant with #id: %s not exist", id));
        }

        return resultList.get(0);
    }

    @Override
    public RestaurantId generateId() {
        return RestaurantId.of(
                UUID.randomUUID().toString()
        );
    }

    @Override
    public List<Restaurant> findAll(int page, int size) {
        return entityManager.createQuery("select r from RestaurantEntity r", Restaurant.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public boolean exists(String restaurantId) {
        List<Restaurant> resultList = entityManager.createQuery(
                        "select r from RestaurantEntity r WHERE r.id = :id",
                        Restaurant.class
                )
                .setParameter("id", restaurantId)
                .getResultList();

        return !resultList.isEmpty();
    }

    @Override
    public List<Restaurant> findByName(String name) {
        return entityManager.createQuery(
                "select r from RestaurantEntity r where r.name LIKE CONCAT('%', :name, '%')",
                Restaurant.class
        )
                .setParameter("name", name)
                .getResultList();
    }
}
