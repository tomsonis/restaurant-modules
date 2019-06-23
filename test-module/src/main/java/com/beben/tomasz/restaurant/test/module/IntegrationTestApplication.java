package com.beben.tomasz.restaurant.test.module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ImportAutoConfiguration({
        TestCqrsConfiguration.class,
        TestPersistenceConfiguration.class
})
@EnableConfigurationProperties
@EnableTransactionManagement
public class IntegrationTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntegrationTestApplication.class, args);
    }
}
