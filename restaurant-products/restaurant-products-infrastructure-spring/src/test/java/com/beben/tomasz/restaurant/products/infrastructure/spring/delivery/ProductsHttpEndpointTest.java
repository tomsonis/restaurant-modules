package com.beben.tomasz.restaurant.products.infrastructure.spring.delivery;

import com.beben.tomasz.restaurant.products.BaseProductIntegrationTest;
import com.beben.tomasz.restaurant.products.api.command.AddProductCommand;
import com.beben.tomasz.restaurant.products.api.view.CategoryProductsView;
import com.beben.tomasz.restaurant.products.domain.Product;
import com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.ProductsDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductsHttpEndpointTest extends BaseProductIntegrationTest {

    @Autowired
    private ProductsDatabase productsDatabase;

    @AfterMethod
    public void tearDown() {
        productsDatabase.cleanAll();
    }

    @Test
    public void testAddProduct() throws URISyntaxException {

        //given
        AddProductCommand addProductCommand = AddProductCommand.of(
                ProductsDatabase.TEST_PROD_NAME,
                ProductsDatabase.TEST_PROD_DESC,
                Collections.singletonList(ProductsDatabase.TEST_INGREDIENT_ID),
                Collections.singletonList(ProductsDatabase.TEST_ALLERGEN_ID),
                ProductsDatabase.TEST_PRODUCT_ID,
                ProductsDatabase.TEST_PRODUCT_PRICE,
                ProductsDatabase.TEST_PHOTO_URL,
                ProductsDatabase.TEST_AVAILABLE,
                ProductsDatabase.TEST_CATEGORY_ID,
                ProductsDatabase.TEST_RESTAURANT_REFERENCE
        );

        //when
        Response response = client().target(getRestUri())
                .path("products")
                .request()
                .post(Entity.json(addProductCommand));

        //then
        assertThat(response.getStatus()).isEqualTo(200);

        String productId = response.readEntity(String.class);

        Product product = productsDatabase.findById(productId);
        assertThat(product).isNotNull();
    }

    @Test
    public void testProductsByRestaurantAndGroupedByCategory() throws URISyntaxException {

        //given
        Product product = productsDatabase.saveProduct();

        //when
        Response response = client().target(getRestUri())
                .path("products")
                .path("restaurant")
                .path(product.getRestaurantReference())
                .request()
                .get();


        //then
        assertThat(response.getStatus()).isEqualTo(200);

        List<CategoryProductsView> categoryProductsViews = response.readEntity(new GenericType<List<CategoryProductsView>>() {});
        assertThat(categoryProductsViews).isNotNull();
        assertThat(categoryProductsViews).hasSizeGreaterThan(0);

    }
}