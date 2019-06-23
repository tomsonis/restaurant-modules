package com.beben.tomasz.restaurant.standalone.handler;

import com.beben.tomasz.cqrs.api.command.CommandExecutor;
import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import com.beben.tomasz.cqrs.application.DefaultCommandExecutor;
import com.beben.tomasz.cqrs.application.DefaultQueryExecutor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CqrsConfiguration {

    @Bean
    SpringHandlerInitializer handlerInitializer(
            ConfigurableListableBeanFactory configurableListableBeanFactory
    ) {
        return new SpringHandlerInitializer(
                configurableListableBeanFactory
        );
    }

    @Bean
    CommandExecutor commandExecutor(
            SpringHandlerInitializer springHandlerInitializer
    ) {
        return new DefaultCommandExecutor(
                springHandlerInitializer
        );
    }

    @Bean
    QueryExecutor queryExecutor(
            SpringHandlerInitializer springHandlerInitializer
    ) {
        return new DefaultQueryExecutor(
                springHandlerInitializer
        );
    }
}
