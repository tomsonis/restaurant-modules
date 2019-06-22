package com.beben.tomasz.restaurant.user;

import com.beben.tomasz.restaurant.test.module.BaseIntegrationTest;
import com.beben.tomasz.restaurant.user.configuration.TestUsersConfiguration;
import com.beben.tomasz.restaurant.user.infrastructure.spring.configuration.UserModuleConfiguration;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {
        UserModuleConfiguration.class,
        TestUsersConfiguration.class
})
public class BaseUserIntegrationTest extends BaseIntegrationTest {
}
