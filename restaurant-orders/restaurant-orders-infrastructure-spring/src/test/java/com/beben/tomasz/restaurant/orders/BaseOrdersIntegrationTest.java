package com.beben.tomasz.restaurant.orders;

import com.beben.tomasz.restaurant.orders.configuration.TestOrdersConfiguration;
import com.beben.tomasz.restaurant.orders.infrastructure.spring.configuration.OrdersConfiguration;
import com.beben.tomasz.restaurant.test.module.BaseIntegrationTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {
        OrdersConfiguration.class,
        TestOrdersConfiguration.class
})
public class BaseOrdersIntegrationTest extends BaseIntegrationTest {
}
