package com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.write.product;

import com.beben.tomasz.restaurant.products.domain.Allergen;
import com.beben.tomasz.restaurant.products.domain.Category;
import com.beben.tomasz.restaurant.products.domain.Ingredient;
import com.beben.tomasz.restaurant.products.domain.Product;
import com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.write.allergen.AllergenEntity;
import com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.write.category.CategoryEntity;
import com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.write.ingredient.IngredientEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Table
@Entity
@ToString
@EqualsAndHashCode
@NoArgsConstructor(staticName = "newInstance")
@AllArgsConstructor(staticName = "of")
@EntityListeners(AuditingEntityListener.class)
public class ProductEntity implements Product {

    @Id
    private String id;

    private String name;

    private String description;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(name = "product_ingredients",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private Collection<IngredientEntity> ingredientEntities;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(name = "product_allergens",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "allergen_id")
    )
    private Collection<AllergenEntity> allergenEntities;

    private BigDecimal price;

    private String photoUrl;

    private boolean available;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;

    private String restaurantReference;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    private ProductEntity(
            String id,
            String name,
            String description,
            Collection<IngredientEntity> ingredientEntities,
            Collection<AllergenEntity> allergenEntities,
            BigDecimal price,
            String photoUrl,
            boolean available,
            CategoryEntity categoryEntity,
            String restaurantReference
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredientEntities = ingredientEntities;
        this.allergenEntities = allergenEntities;
        this.price = price;
        this.photoUrl = photoUrl;
        this.available = available;
        this.categoryEntity = categoryEntity;
        this.restaurantReference = restaurantReference;
    }

    public static ProductEntity of(
            String id,
            String name,
            String description,
            Collection<IngredientEntity> ingredientEntities,
            Collection<AllergenEntity> allergenEntities,
            BigDecimal price,
            String photoUrl,
            boolean available,
            CategoryEntity categoryEntity,
            String restaurantReference
    ) {
        return new ProductEntity(
                id,
                name,
                description,
                ingredientEntities,
                allergenEntities,
                price,
                photoUrl,
                available,
                categoryEntity,
                restaurantReference
        );
    }
    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Collection<Ingredient> getIngredients() {
        return new ArrayList<>(ingredientEntities);
    }

    @Override
    public Collection<Allergen> getAllergens() {
        return new ArrayList<>(allergenEntities);
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String getPhotoUrl() {
        return photoUrl;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public Category getCategory() {
        return categoryEntity;
    }

    @Override
    public String getRestaurantReference() {
        return restaurantReference;
    }
}
