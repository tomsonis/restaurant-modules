package com.beben.tomasz.restaurant.user.application.query;

import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import com.beben.tomasz.restaurant.user.BaseUserIntegrationTest;
import com.beben.tomasz.restaurant.user.application.UserIdQuery;
import com.beben.tomasz.restaurant.user.domain.ApplicationUser;
import com.beben.tomasz.restaurant.user.domain.UserId;
import com.beben.tomasz.restaurant.user.infrastructure.spring.persistance.UsersDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserIdQueryHandlerTest extends BaseUserIntegrationTest {

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
        UserIdQuery userIdQuery = UserIdQuery.of(UsersDatabase.TEST_USERNAME);

        //when
        UserId userId = queryExecutor.execute(userIdQuery);

        //then
        assertThat(userId).isNotNull();
        assertThat(userId).isEqualTo(applicationUser.getUserId());
    }
}