package com.beben.tomasz.restaurant.user.infrastructure.spring.configuration;

import com.beben.tomasz.cqrs.api.command.CommandExecutor;
import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import com.beben.tomasz.restaurant.user.infrastructure.spring.delivery.UserHttpEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserHttpEndpointsConfiguration {

    @Bean
    UserHttpEndpoint userHttpEndpoint(
            CommandExecutor commandExecutor,
            QueryExecutor queryExecutor
    ) {
        return new UserHttpEndpoint(
                commandExecutor,
                queryExecutor
        );
    }
}
