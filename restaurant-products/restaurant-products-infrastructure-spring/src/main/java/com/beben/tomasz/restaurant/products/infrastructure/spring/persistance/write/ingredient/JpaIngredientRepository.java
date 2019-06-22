package com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.write.ingredient;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
@RepositoryRestResource(
        path = "ingredients",
        collectionResourceRel = "ingredients",
        itemResourceRel = "ingredient"
)
public interface JpaIngredientRepository extends CrudRepository<IngredientEntity, String> {
}
