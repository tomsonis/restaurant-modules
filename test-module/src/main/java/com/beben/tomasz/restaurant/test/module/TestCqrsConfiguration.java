package com.beben.tomasz.restaurant.test.module;

import com.beben.tomasz.cqrs.api.HandlerInitializer;
import com.beben.tomasz.cqrs.api.command.CommandExecutor;
import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import com.beben.tomasz.cqrs.application.DefaultCommandExecutor;
import com.beben.tomasz.cqrs.application.DefaultQueryExecutor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestCqrsConfiguration {

    @Bean
    HandlerInitializer testHandlerInitializer(
            ConfigurableListableBeanFactory configurableListableBeanFactory
    ) {
        return new TestHandlerInitializer(
                configurableListableBeanFactory
        );
    }

    @Bean
    CommandExecutor testCommandExecutor(
            HandlerInitializer testHandlerInitializer
    ) {
        return new DefaultCommandExecutor(
                testHandlerInitializer
        );
    }

    @Bean
    QueryExecutor testQueryExecutor(
            HandlerInitializer testHandlerInitializer
    ) {
        return new DefaultQueryExecutor(
                testHandlerInitializer
        );
    }
}
