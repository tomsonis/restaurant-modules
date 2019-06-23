package com.beben.tomasz.restaurant.standalone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@EnableJpaRepositories(
        basePackages = {
                "com.beben.tomasz.restaurant.products.infrastructure.spring.persistance"
        },
        entityManagerFactoryRef = "localContainerEntityManagerFactoryBean"
)
@EnableAsync
public class RestaurantApp {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantApp.class, args);
    }
}
