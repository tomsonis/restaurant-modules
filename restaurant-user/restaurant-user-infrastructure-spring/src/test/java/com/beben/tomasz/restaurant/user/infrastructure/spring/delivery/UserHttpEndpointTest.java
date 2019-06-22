package com.beben.tomasz.restaurant.user.infrastructure.spring.delivery;

import com.beben.tomasz.restaurant.user.BaseUserIntegrationTest;
import com.beben.tomasz.restaurant.user.api.ClientRequest;
import com.beben.tomasz.restaurant.user.api.command.RegisterUserCommand;
import com.beben.tomasz.restaurant.user.api.view.UserDetailsView;
import com.beben.tomasz.restaurant.user.domain.ApplicationUser;
import com.beben.tomasz.restaurant.user.domain.UserRepository;
import com.beben.tomasz.restaurant.user.infrastructure.spring.persistance.UsersDatabase;
import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class UserHttpEndpointTest extends BaseUserIntegrationTest {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UsersDatabase usersDatabase;

    @AfterMethod
    public void tearDown() {
        usersDatabase.clearAll();
    }

    @Test
    public void testRegisterUser() throws URISyntaxException {

        //given
        RegisterUserCommand registerUserCommand = RegisterUserCommand.of(
                UsersDatabase.TEST_USERNAME,
                UsersDatabase.TEST_PASSWORD,
                UsersDatabase.TEST_EMAIL,
                ClientRequest.of(
                        UsersDatabase.TEST_CLIENT_NAME,
                        UsersDatabase.TEST_CLIENT_SURNAME,
                        UsersDatabase.TEST_PHONE_NUMBER
                ),
                UsersDatabase.TEST_RESTAURANT_REFERENCE,
                Collections.emptyList()
        );

        //when
        Response response = client().target(getRestUri())
                .path("user")
                .path("register")
                .request()
                .post(Entity.json(registerUserCommand));


        //then
        assertThat(response.getStatus()).isEqualTo(200);

        String userId = response.readEntity(String.class);
        assertThat(userId).isNotBlank();

        Option<ApplicationUser> applicationUser = userRepository.findByUserId(userId);
        assertThat(applicationUser).isNotNull();

        ApplicationUser actual = applicationUser.get();
        assertThat(actual).isNotNull();
        assertThat(actual.getUsername()).isEqualTo(registerUserCommand.getUsername());
    }

    @Test
    public void testUserDetails() throws URISyntaxException {
        //given
        ApplicationUser applicationUser = usersDatabase.saveUser();

        //when
        Response response = client()
                .target(getRestUri())
                .path("user")
                .path("details")
                .request()
                .get();

        assertThat(response.getStatus()).isEqualTo(200);

        UserDetailsView userDetailsView = response.readEntity(UserDetailsView.class);
        assertThat(userDetailsView).isNotNull();
        assertThat(userDetailsView.getName()).isEqualTo(applicationUser.getRestaurantClient().getName());
    }
}