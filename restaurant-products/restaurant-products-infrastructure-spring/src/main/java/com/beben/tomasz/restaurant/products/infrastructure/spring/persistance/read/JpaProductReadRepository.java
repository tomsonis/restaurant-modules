package com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.read;

import com.beben.tomasz.restaurant.products.application.query.ProductsReadRepository;
import com.beben.tomasz.restaurant.products.domain.Category;
import com.beben.tomasz.restaurant.products.domain.Product;
import com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.write.product.QProductEntity;
import com.querydsl.core.group.GroupBy;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class JpaProductReadRepository implements ProductsReadRepository {

    private EntityManager entityManager;


    @Override
    public List<Product> findAllByCategory(String categoryId, String restaurantReference) {
        QProductEntity productEntity = QProductEntity.productEntity;

        return new JPAQuery<Product>(entityManager)
                .from(productEntity)
                .where(productEntity.categoryEntity.id.equalsIgnoreCase(categoryId))
                .where(productEntity.restaurantReference.eq(restaurantReference))
                .fetch();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<Category, List<Product>> findProductsGroupedByCategory(String restaurantId) {
        QProductEntity productEntity = QProductEntity.productEntity;
        return (Map) new JPAQuery<>(entityManager)
                .from(productEntity)
                .where(productEntity.restaurantReference.eq(restaurantId))
                .where(productEntity.available.isTrue())
                .orderBy(productEntity.categoryEntity.orderOnList.asc())
                .transform(GroupBy.groupBy(productEntity.categoryEntity).as(GroupBy.list(productEntity)));
    }

    @Override
    public List<Product> findByIds(List<String> productIds) {
        QProductEntity productEntity = QProductEntity.productEntity;
        return new JPAQuery<Product>(entityManager)
                .from(productEntity)
                .where(productEntity.id.in(productIds))
                .fetch();
    }
}
