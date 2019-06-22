package com.beben.tomasz.restaurant.products.infrastructure.spring.persistance;

import com.beben.tomasz.restaurant.products.domain.Product;
import com.beben.tomasz.restaurant.products.domain.UnitEnum;
import com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.write.allergen.AllergenEntity;
import com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.write.category.CategoryEntity;
import com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.write.ingredient.IngredientEntity;
import com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.write.product.ProductEntity;
import com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.write.volume.VolumeEntity;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Collections;

@Transactional
@AllArgsConstructor
public class ProductsDatabase {

    private static final String TEST_VOLUME_ID = "TEST_VOLUME_ID";
    private static final int TEST_VOLUME_CAPACITY = 250;
    private static final String TEST_NAME = "TEST_NAME";
    public static final String TEST_INGREDIENT_ID = "TEST_INGREDIENT_ID";
    public static final String TEST_RESTAURANT_REFERENCE = "TEST_RESTAURANT_REFERENCE";
    public static final String TEST_CATEGORY_ID = "TEST_VOLUME_ID";
    private static final int TEST_ORDER_ON_LIST = 0;
    private static final String TEST_DESC = "TEST_DESC";
    public static final String TEST_ALLERGEN_ID = "TEST_VOLUME_ID";
    public static final String TEST_PRODUCT_ID = "TEST_PRODUCT_ID";
    public static final String TEST_PROD_NAME = "TEST_PROD_NAME";
    public static final String TEST_PROD_DESC = "TEST_PROD_DESC";
    public static final String TEST_PHOTO_URL = "TEST_PHOTO_URL";
    public static final boolean TEST_AVAILABLE = true;
    public static final BigDecimal TEST_PRODUCT_PRICE = BigDecimal.valueOf(120.56);

    private EntityManager entityManager;

    public void cleanAll() {
        entityManager.createQuery("DELETE FROM ProductEntity").executeUpdate();
        entityManager.createQuery("DELETE FROM IngredientEntity").executeUpdate();
        entityManager.createQuery("DELETE FROM VolumeEntity").executeUpdate();
        entityManager.createQuery("DELETE FROM AllergenEntity").executeUpdate();
        entityManager.createQuery("DELETE FROM CategoryEntity").executeUpdate();
    }

    public Product findById(String id) {
        return entityManager.find(ProductEntity.class, id);
    }


    public Product saveProduct() {
        IngredientEntity ingredient = saveIngredient();
        AllergenEntity allergen = saveAllergen();
        CategoryEntity category = saveCategory();

        Product product = createProduct(ingredient, allergen, category);
        entityManager.persist(product);

        return product;
    }

    private CategoryEntity saveCategory() {
        CategoryEntity category = createCategory();
        entityManager.persist(category);

        return category;
    }

    private CategoryEntity createCategory() {
        return CategoryEntity.of(
                TEST_CATEGORY_ID,
                TEST_NAME,
                TEST_ORDER_ON_LIST,
                TEST_RESTAURANT_REFERENCE
        );
    }

    private AllergenEntity saveAllergen() {
        AllergenEntity allergen = createAllergen();
        entityManager.persist(allergen);

        return allergen;
    }

    private AllergenEntity createAllergen() {
        return AllergenEntity.of(
                TEST_ALLERGEN_ID,
                TEST_NAME,
                TEST_DESC,
                TEST_RESTAURANT_REFERENCE
        );
    }

    private IngredientEntity saveIngredient() {
        VolumeEntity volumeEntity = saveVolume();

        IngredientEntity ingredient = createIngredient(volumeEntity);

        entityManager.persist(ingredient);

        return ingredient;
    }

    private IngredientEntity createIngredient(VolumeEntity volumeEntity) {
        return IngredientEntity.of(
                TEST_INGREDIENT_ID,
                TEST_NAME,
                TEST_RESTAURANT_REFERENCE,
                volumeEntity
        );
    }

    private VolumeEntity saveVolume() {
        VolumeEntity volumeEntity = createVolumeEntity();

        entityManager.persist(volumeEntity);

        return volumeEntity;
    }

    private VolumeEntity createVolumeEntity() {
        return VolumeEntity.of(
                TEST_VOLUME_ID,
                TEST_VOLUME_CAPACITY,
                UnitEnum.G
        );
    }

    private Product createProduct(IngredientEntity ingredient, AllergenEntity allergen, CategoryEntity category) {
        return ProductEntity.of(
                TEST_PRODUCT_ID,
                TEST_PROD_NAME,
                TEST_PROD_DESC,
                Collections.singletonList(ingredient),
                Collections.singletonList(allergen),
                TEST_PRODUCT_PRICE,
                TEST_PHOTO_URL,
                TEST_AVAILABLE,
                category,
                TEST_RESTAURANT_REFERENCE
        );
    }
}
