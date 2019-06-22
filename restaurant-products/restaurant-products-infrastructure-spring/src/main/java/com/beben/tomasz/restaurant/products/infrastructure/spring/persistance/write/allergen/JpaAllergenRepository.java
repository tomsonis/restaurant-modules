package com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.write.allergen;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(
        path = "allergens",
        collectionResourceRel = "allergens",
        itemResourceRel = "allergen"
)
public interface JpaAllergenRepository extends CrudRepository<AllergenEntity, String> {
}
