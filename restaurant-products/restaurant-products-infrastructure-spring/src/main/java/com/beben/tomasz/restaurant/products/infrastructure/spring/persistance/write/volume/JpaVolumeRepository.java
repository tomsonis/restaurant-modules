package com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.write.volume;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(
        path = "volumes",
        collectionResourceRel = "volumes",
        itemResourceRel = "volume"
)
public interface JpaVolumeRepository extends CrudRepository<VolumeEntity, String> {
}
