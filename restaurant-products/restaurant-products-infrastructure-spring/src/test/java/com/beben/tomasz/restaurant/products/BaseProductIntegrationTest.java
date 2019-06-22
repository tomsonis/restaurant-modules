package com.beben.tomasz.restaurant.products;

import com.beben.tomasz.restaurant.products.configuration.TestProductsConfiguration;
import com.beben.tomasz.restaurant.products.infrastructure.spring.configuration.ProductsConfiguration;
import com.beben.tomasz.restaurant.test.module.BaseIntegrationTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {
        ProductsConfiguration.class,
        TestProductsConfiguration.class
})
@EnableJpaRepositories(
        basePackages = {
                "com.beben.tomasz.restaurant.products.infrastructure.spring.persistance"
        },
        entityManagerFactoryRef = "localContainerEntityManagerFactoryBean",
        transactionManagerRef = "platformTransactionManager"
)
public abstract class BaseProductIntegrationTest extends BaseIntegrationTest {
}