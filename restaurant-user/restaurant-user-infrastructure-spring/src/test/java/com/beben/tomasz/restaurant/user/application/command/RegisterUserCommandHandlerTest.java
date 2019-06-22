package com.beben.tomasz.restaurant.user.application.command;

import com.beben.tomasz.cqrs.api.command.CommandExecutor;
import com.beben.tomasz.restaurant.user.BaseUserIntegrationTest;
import com.beben.tomasz.restaurant.user.api.ClientRequest;
import com.beben.tomasz.restaurant.user.api.command.RegisterUserCommand;
import com.beben.tomasz.restaurant.user.domain.ApplicationUser;
import com.beben.tomasz.restaurant.user.domain.UserRepository;
import com.beben.tomasz.restaurant.user.infrastructure.spring.persistance.UsersDatabase;
import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class RegisterUserCommandHandlerTest extends BaseUserIntegrationTest {

    @Autowired
    private CommandExecutor commandExecutor;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UsersDatabase usersDatabase;

    @AfterMethod
    public void setUp() {
        usersDatabase.clearAll();
    }

    @Test
    public void shouldRegisterUser() throws Exception {

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

        String userId = commandExecutor.execute(registerUserCommand);

        //then
        assertThat(userId).isNotBlank();

        Option<ApplicationUser> applicationUser = userRepository.findByUserId(userId);
        assertThat(applicationUser).isNotNull();

        ApplicationUser actual = applicationUser.get();
        assertThat(actual).isNotNull();
        assertThat(actual.getUsername()).isEqualTo(registerUserCommand.getUsername());
    }
}