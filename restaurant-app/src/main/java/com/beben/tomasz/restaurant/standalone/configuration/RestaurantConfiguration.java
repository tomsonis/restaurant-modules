package com.beben.tomasz.restaurant.standalone.configuration;

import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import com.beben.tomasz.restaurant.commons.ContextHolder;
import com.beben.tomasz.restaurant.commons.mail.EmailService;
import com.beben.tomasz.restaurant.core.infrastructure.spring.configuration.RestaurantCoreConfiguration;
import com.beben.tomasz.restaurant.orders.infrastructure.spring.configuration.OrdersConfiguration;
import com.beben.tomasz.restaurant.products.infrastructure.spring.configuration.ProductsConfiguration;
import com.beben.tomasz.restaurant.standalone.context.ContextHolderImpl;
import com.beben.tomasz.restaurant.standalone.mail.AsyncEmailService;
import com.beben.tomasz.restaurant.standalone.security.configuration.SecurityConfiguration;
import com.beben.tomasz.restaurant.standalone.security.configuration.WebSecurity;
import com.beben.tomasz.restaurant.user.infrastructure.spring.configuration.UserModuleConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;
import java.util.Properties;


@EnableJpaAuditing
@Configuration
@Import({
        OrdersConfiguration.class,
        ProductsConfiguration.class,
        RestaurantCoreConfiguration.class,
        UserModuleConfiguration.class,
        SecurityConfiguration.class
})
public class RestaurantConfiguration {

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
                "com.beben.tomasz.restaurant.orders.infrastructure.spring.persistance",
                "com.beben.tomasz.restaurant.products.infrastructure.spring.persistance",
                "com.beben.tomasz.restaurant.core.infrastructure.spring.persistence",
                "com.beben.tomasz.restaurant.user.infrastructure.spring.persistance"

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
    ContextHolderImpl contextHolder() {
        return new ContextHolderImpl();
    }

    @Bean
    WebSecurity webSecurity(
            UserDetailsService userDetailsService,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            ContextHolder contextHolder,
            QueryExecutor queryExecutor
    ) {
        return new WebSecurity(
                userDetailsService,
                bCryptPasswordEncoder,
                contextHolder,
                queryExecutor
        );
    }

    @Bean
    EmailService defaultEmailService(
            JavaMailSender javaMailSender
    ) {
        return new AsyncEmailService(
                javaMailSender
        );
    }

//    @Bean
//    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
//        return registry -> registry.config().commonTags("application", "MYAPPNAME");
//    }
}
