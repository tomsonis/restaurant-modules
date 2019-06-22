package com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.write.product;

import com.beben.tomasz.restaurant.products.domain.Product;
import com.beben.tomasz.restaurant.products.domain.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.UUID;

@Transactional
@AllArgsConstructor
public class JpaProductWriteRepository implements ProductRepository {

    private final EntityManager entityManager;

    @Override
    public String generateId() {
        return "PRD-" + UUID.randomUUID().toString();
    }

    @Override
    public void save(Product product) {
        entityManager.persist(product);
    }
}
