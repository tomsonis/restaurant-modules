package com.beben.tomasz.restaurant.products.application.command;


import com.beben.tomasz.cqrs.api.command.CommandExecutor;
import com.beben.tomasz.restaurant.products.BaseProductIntegrationTest;
import com.beben.tomasz.restaurant.products.api.command.AddProductCommand;
import com.beben.tomasz.restaurant.products.domain.Product;
import com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.ProductsDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class AddProductCommandHandlerIT extends BaseProductIntegrationTest {


    @Autowired
    private CommandExecutor commandExecutor;

    @Autowired
    private ProductsDatabase productsDatabase;

    @AfterMethod
    public void tearDown() {
        productsDatabase.cleanAll();
    }


    @Test
    public void testHandle() throws Exception {
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

        String productId = commandExecutor.execute(addProductCommand);

        assertThat(productId).isNotBlank();

        Product product = productsDatabase.findById(productId);

        assertThat(product.getName()).isEqualTo(ProductsDatabase.TEST_PROD_NAME);
        assertThat(product.getDescription()).isEqualTo(ProductsDatabase.TEST_PROD_DESC);
        assertThat(product.getPrice()).isEqualTo(ProductsDatabase.TEST_PRODUCT_PRICE);
    }
}