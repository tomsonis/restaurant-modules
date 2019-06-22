package com.beben.tomasz.restaurant.test.module;

import com.beben.tomasz.restaurant.commons.ContextHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "com.beben.tomasz.restaurant.core.infrastructure.spring.persistence")
public class TestPersistenceConfiguration {


    @Bean
    LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(
            DataSource dataSource,
            Properties jpaProperties,
            JpaVendorAdapter jpaVendorAdapter
    ) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setJpaVendorAdapter(jpaVendorAdapter);
        em.setPackagesToScan(
                "com.beben.tomasz.restaurant.core.infrastructure.spring.persistence",
                "com.beben.tomasz.restaurant.products.infrastructure.spring.persistance",
                "com.beben.tomasz.restaurant.user.infrastructure.spring.persistance",
                "com.beben.tomasz.restaurant.orders.infrastructure.spring.persistance"
        );
        em.setJpaProperties(jpaProperties);

        return em;
    }

    @Bean
    Properties jpaProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
        properties.setProperty("javax.persistence.schema-generation.database.action", "update");

        return properties;
    }

    @Bean
    JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    PlatformTransactionManager platformTransactionManager(
            EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(
                entityManagerFactory
        );
    }

    @Bean
    ContextHolder contextHolder() {
        return new ContextHolderImpl();
    }
}
