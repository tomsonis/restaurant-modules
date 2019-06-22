package com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.write.category;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(
        path = "categories",
        collectionResourceRel = "categories",
        itemResourceRel = "category"
)
public interface JpaCategoryWriteRepository extends CrudRepository<CategoryEntity, String> {

    @RestResource(path = "restaurant")
    List<CategoryEntity> findByRestaurantReferenceOrderByOrderOnListAsc(@Param("restaurantReference") String restaurantReference);
}
