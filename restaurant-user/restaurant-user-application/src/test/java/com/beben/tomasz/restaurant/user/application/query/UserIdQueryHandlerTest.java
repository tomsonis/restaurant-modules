package com.beben.tomasz.restaurant.user.application.query;

import com.beben.tomasz.restaurant.user.application.UserIdQuery;
import com.beben.tomasz.restaurant.user.application.model.TestApplicationUser;
import com.beben.tomasz.restaurant.user.domain.UserId;
import com.beben.tomasz.restaurant.user.domain.UserRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.beben.tomasz.restaurant.user.application.model.TestApplicationUser.TEST_USERNAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class UserIdQueryHandlerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserIdQueryHandler userIdQueryHandler;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testHandle() throws Exception {

        //given
        UserIdQuery userIdQuery = UserIdQuery.of(TEST_USERNAME);

        TestApplicationUser testApplicationUser = new TestApplicationUser();

        //when
        when(userRepository.findByUsername(TEST_USERNAME))
                .thenReturn(testApplicationUser);

        UserId userId = userIdQueryHandler.handle(userIdQuery);

        //then
        verify(userRepository).findByUsername(TEST_USERNAME);
        verifyNoMoreInteractions(userRepository);

        assertThat(userId).isNotNull();
        assertThat(userId.getId()).isNotBlank();
    }
}