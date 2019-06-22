package com.beben.tomasz.restaurant.core;

import com.beben.tomasz.restaurant.core.configuration.TestRestaurantCoreConfiguration;
import com.beben.tomasz.restaurant.core.infrastructure.spring.configuration.RestaurantCoreConfiguration;
import com.beben.tomasz.restaurant.test.module.BaseIntegrationTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;


@ContextConfiguration(classes = {
        RestaurantCoreConfiguration.class,
        TestRestaurantCoreConfiguration.class
})
@Configuration
public class BaseCoreIntegrationTest extends BaseIntegrationTest {


}
