package com.beben.tomasz.restaurant.products.application.query.list;

import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import com.beben.tomasz.restaurant.products.BaseProductIntegrationTest;
import com.beben.tomasz.restaurant.products.api.query.SearchProductsGroupedByCategoriesQuery;
import com.beben.tomasz.restaurant.products.api.view.CategoryProductsView;
import com.beben.tomasz.restaurant.products.api.view.CategoryView;
import com.beben.tomasz.restaurant.products.api.view.ProductView;
import com.beben.tomasz.restaurant.products.domain.Category;
import com.beben.tomasz.restaurant.products.domain.Product;
import com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.ProductsDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchProductsGroupedByCategoriesQueryHandlerIT extends BaseProductIntegrationTest {


    @Autowired
    private QueryExecutor queryExecutor;

    @Autowired
    private ProductsDatabase productsDatabase;

    @AfterMethod
    public void tearDown() {
        productsDatabase.cleanAll();
    }

    @Test
    public void shouldFindProductsGroupedByCategory() throws Exception {
        Product product = productsDatabase.saveProduct();

        SearchProductsGroupedByCategoriesQuery productsGroupedByCategoriesQuery = SearchProductsGroupedByCategoriesQuery.of(
                ProductsDatabase.TEST_RESTAURANT_REFERENCE
        );

        List<CategoryProductsView> categoryProductsViews = queryExecutor.execute(productsGroupedByCategoriesQuery);

        //then
        assertThat(categoryProductsViews).isNotNull();
        assertThat(categoryProductsViews).hasSizeGreaterThan(0);

        CategoryProductsView categoryProductsView = categoryProductsViews.get(0);
        Category productCategory = product.getCategory();

        CategoryView category = categoryProductsView.getCategory();
        assertThat(category.getName()).isEqualTo(productCategory.getName());

        List<ProductView> products = categoryProductsView.getProducts();
        assertThat(products).isNotNull();
        assertThat(products).hasSizeGreaterThan(0);

        ProductView productView = products.get(0);
        assertThat(productView.getName()).isEqualTo(product.getName());
    }
}
