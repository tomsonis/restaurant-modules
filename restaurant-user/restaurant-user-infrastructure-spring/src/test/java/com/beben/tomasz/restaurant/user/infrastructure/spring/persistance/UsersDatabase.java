package com.beben.tomasz.restaurant.user.infrastructure.spring.persistance;

import com.beben.tomasz.restaurant.commons.ContextHolder;
import com.beben.tomasz.restaurant.test.module.ContextHolderImpl;
import com.beben.tomasz.restaurant.user.domain.ApplicationUser;
import com.beben.tomasz.restaurant.user.infrastructure.spring.persistance.user.RestaurantUserEntity;
import com.beben.tomasz.restaurant.user.infrastructure.spring.persistance.user.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.net.ContentHandler;
import java.util.Collections;

@Transactional
@AllArgsConstructor
public class UsersDatabase {

    public static final String TEST_USERNAME = "TEST_USERNAME";
    public static final String TEST_PASSWORD = "TEST_PASSWORD";
    public static final String TEST_EMAIL = "TEST_EMAIL@Email.com";
    public static final String TEST_CLIENT_NAME = "TEST_CLIENT_NAME";
    public static final String TEST_CLIENT_SURNAME = "TEST_CLIENT_SURNAME";
    public static final String TEST_PHONE_NUMBER = "TEST_PHONE_NUMBER";
    public static final String TEST_RESTAURANT_REFERENCE = "TEST_RESTAURANT_REFERENCE";
    public static final String TEST_CLIENT_ID = "TEST_CLIENT_ID";
    public static final String TEST_RESTAURANT_USER_ID = "TEST_RESTAURANT_USER_ID";


    private EntityManager entityManager;

    public void clearAll() {
        entityManager.createQuery("DELETE FROM UserEntity").executeUpdate();
        entityManager.createQuery("DELETE FROM ClientEntity").executeUpdate();
        entityManager.createQuery("DELETE FROM RestaurantUserEntity").executeUpdate();
    }

    public ApplicationUser saveUser() {
        ApplicationUser applicationUser = new UserEntity(
                ContextHolderImpl.TEST_USER_ID,
                TEST_USERNAME,
                TEST_PASSWORD,
                TEST_EMAIL,
                ClientEntity.of(
                        TEST_CLIENT_ID,
                        TEST_CLIENT_NAME,
                        TEST_CLIENT_SURNAME,
                        TEST_PHONE_NUMBER
                ),
                RestaurantUserEntity.of(
                        TEST_RESTAURANT_USER_ID,
                        TEST_RESTAURANT_REFERENCE,
                        Collections.emptyList()
                )
        );

        entityManager.persist(applicationUser);

        return applicationUser;
    }
}
