package com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.write.product;

import com.beben.tomasz.restaurant.products.domain.Product;
import com.beben.tomasz.restaurant.products.domain.ProductFactory;
import com.beben.tomasz.restaurant.products.domain.ProductRepository;
import com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.write.allergen.AllergenEntity;
import com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.write.allergen.JpaAllergenRepository;
import com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.write.category.CategoryEntity;
import com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.write.category.JpaCategoryWriteRepository;
import com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.write.ingredient.IngredientEntity;
import com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.write.ingredient.JpaIngredientRepository;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
public class DefaultProductFactory implements ProductFactory {

    private ProductRepository productRepository;

    private JpaIngredientRepository jpaIngredientRepository;

    private JpaAllergenRepository jpaAllergenRepository;

    private JpaCategoryWriteRepository jpaCategoryWriteRepository;

    @Override
    public Product createProduct(
            String name,
            String description,
            Collection<String> ingredientIds,
            Collection<String> allergenIds,
            String volumeId,
            BigDecimal price,
            String photoUrl,
            boolean available,
            String categoryId,
            String restaurantReference

    ) {
        return ProductEntity.of(
                productRepository.generateId(),
                name,
                description,
                createIngredientEntities(ingredientIds),
                createAllergenEntities(allergenIds),
                price,
                photoUrl,
                available,
                createCategoryEntity(categoryId),
                restaurantReference
        );
    }

    private Collection<IngredientEntity> createIngredientEntities(Collection<String> ingredientIds) {
        Iterable<IngredientEntity> ingredientEntities = jpaIngredientRepository.findAllById(ingredientIds);
        return StreamSupport.stream(ingredientEntities.spliterator(), false)
                .collect(Collectors.toList());
    }

    private Collection<AllergenEntity> createAllergenEntities(Collection<String> allergenIds) {
        Iterable<AllergenEntity> allergenEntities = jpaAllergenRepository.findAllById(allergenIds);
        return StreamSupport.stream(allergenEntities.spliterator(), false)
                .collect(Collectors.toList());
    }

    private CategoryEntity createCategoryEntity(String categoryId) {
        return jpaCategoryWriteRepository.findById(categoryId)
                .orElse(null);
    }
}
