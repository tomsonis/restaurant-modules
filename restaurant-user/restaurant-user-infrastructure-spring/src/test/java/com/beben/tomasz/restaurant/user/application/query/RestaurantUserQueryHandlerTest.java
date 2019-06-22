package com.beben.tomasz.restaurant.user.application.query;

import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import com.beben.tomasz.restaurant.user.BaseUserIntegrationTest;
import com.beben.tomasz.restaurant.user.api.query.RestaurantUserQuery;
import com.beben.tomasz.restaurant.user.api.view.RestaurantUserView;
import com.beben.tomasz.restaurant.user.domain.ApplicationUser;
import com.beben.tomasz.restaurant.user.infrastructure.spring.persistance.UsersDatabase;
import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantUserQueryHandlerTest extends BaseUserIntegrationTest {

    @Autowired
    private QueryExecutor queryExecutor;

    @Autowired
    private UsersDatabase usersDatabase;

    @AfterMethod
    public void setUp() {
        usersDatabase.clearAll();
    }

    @Test
    public void testHandle() throws Exception {

        //given
        ApplicationUser applicationUser = usersDatabase.saveUser();
        RestaurantUserQuery restaurantUserQuery = RestaurantUserQuery.of();

        //when
        Option<RestaurantUserView> restaurantUserView = queryExecutor.execute(restaurantUserQuery);

        //then
        assertThat(restaurantUserView).isNotNull();

        assertThat(restaurantUserView.get().getRestaurantReference()).isEqualTo(applicationUser.getRestaurantUser().getRestaurantReference());
    }
}