package com.beben.tomasz.restaurant.user.application.command;

import com.beben.tomasz.restaurant.user.api.ClientRequest;
import com.beben.tomasz.restaurant.user.api.command.RegisterUserCommand;
import com.beben.tomasz.restaurant.user.application.model.TestApplicationUser;
import com.beben.tomasz.restaurant.user.domain.*;
import org.mockito.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;

import static com.beben.tomasz.restaurant.user.application.model.TestApplicationUser.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class RegisterUserCommandHandlerTest {

    private static final ClientRequest CLIENT_REQUEST = ClientRequest.of(
            TEST_CLIENT_NAME,
            TEST_CLIENT_SURNAME,
            TEST_CLIENT_PHONE_NUMBER
    );
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserFactory userFactory;

    @Mock
    private ClientFactory clientFactory;

    @Mock
    private RestaurantUserFactory restaurantUserFactory;

    @InjectMocks
    private RegisterUserCommandHandler registerUserCommandHandler;

    @Captor
    private ArgumentCaptor<ApplicationUser> applicationUserArgumentCaptor;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldRegisterUser() throws Exception {

        RegisterUserCommand registerUserCommand = RegisterUserCommand.of(
                TEST_USERNAME,
                TEST_PASSWORD,
                TEST_EMAIL,
                CLIENT_REQUEST,
                TEST_RESTAURANT_REF,
                Collections.emptyList()
        );
        TestApplicationUser testApplicationUser = new TestApplicationUser();

        //when
        when(userRepository.existEmail(TEST_EMAIL))
                .thenReturn(false);
        when(userRepository.existUserName(TEST_USERNAME))
                .thenReturn(false);
        when(userFactory.createUser(anyString(), anyString(),  anyString(), any(RestaurantClient.class), any(RestaurantUser.class)))
                .thenReturn(testApplicationUser);

        String userId = registerUserCommandHandler.handle(registerUserCommand);

        //then
        assertThat(userId).isNotBlank();

        verify(userRepository).existEmail(TEST_EMAIL);
        verify(userRepository).existUserName(TEST_USERNAME);
        verify(userRepository).save(applicationUserArgumentCaptor.capture());
        verifyNoMoreInteractions(userRepository);

        ApplicationUser applicationUser = applicationUserArgumentCaptor.getValue();
        assertThat(applicationUser.getUsername()).isEqualTo(testApplicationUser.getUsername());

    }
}