package com.beben.tomasz.restaurant.products.infrastructure.spring.configuration;

import com.beben.tomasz.restaurant.products.application.query.ProductsReadRepository;
import com.beben.tomasz.restaurant.products.domain.ProductFactory;
import com.beben.tomasz.restaurant.products.domain.ProductRepository;
import com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.read.JpaProductReadRepository;
import com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.write.allergen.JpaAllergenRepository;
import com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.write.category.JpaCategoryWriteRepository;
import com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.write.ingredient.JpaIngredientRepository;
import com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.write.product.DefaultProductFactory;
import com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.write.product.JpaProductWriteRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.persistence.EntityManager;

@Configuration
@Import({
        ProductsHttpEndpointsConfiguration.class,
        ProductCommandHandlersConfiguration.class,
        ProductQueryHandlersConfiguration.class,
        ProductConvertersConfiguration.class
})
public class ProductsConfiguration {

    @Bean
    ProductRepository productRepository(
            EntityManager entityManager
    ) {
        return new JpaProductWriteRepository(
                entityManager
        );
    }

    @Bean
    ProductFactory productFactory(
            ProductRepository productRepository,
            JpaIngredientRepository jpaIngredientRepository,
            JpaAllergenRepository jpaAllergenRepository,
            JpaCategoryWriteRepository jpaCategoryWriteRepository
    ) {
        return new DefaultProductFactory(
                productRepository,
                jpaIngredientRepository,
                jpaAllergenRepository,
                jpaCategoryWriteRepository
        );
    }

    @Bean
    ProductsReadRepository productsReadRepository(
            EntityManager entityManager
    ) {
        return new JpaProductReadRepository(
                entityManager
        );
    }
}
