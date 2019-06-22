package com.beben.tomasz.restaurant.user.configuration;

import com.beben.tomasz.restaurant.user.infrastructure.spring.persistance.UsersDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.EntityManager;

@Configuration
public class TestUsersConfiguration {

    @Bean
    UsersDatabase usersDatabase(EntityManager entityManager) {
        return new UsersDatabase(entityManager);
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
