package com.beben.tomasz.restaurant.standalone.configuration;

import com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.write.category.CategoryEntity;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class RepositoryConfiguration extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(CategoryEntity.class);
    }
}
