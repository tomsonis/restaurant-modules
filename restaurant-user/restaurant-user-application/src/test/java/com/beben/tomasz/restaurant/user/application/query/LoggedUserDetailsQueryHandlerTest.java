package com.beben.tomasz.restaurant.user.application.query;

import com.beben.tomasz.restaurant.commons.ContextHolder;
import com.beben.tomasz.restaurant.commons.UserContext;
import com.beben.tomasz.restaurant.user.api.query.LoggedUserDetailsQuery;
import com.beben.tomasz.restaurant.user.api.view.UserDetailsView;
import com.beben.tomasz.restaurant.user.application.converters.ToUserViewConverter;
import com.beben.tomasz.restaurant.user.application.model.TestApplicationUser;
import com.beben.tomasz.restaurant.user.domain.UserRepository;
import io.vavr.control.Option;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.beben.tomasz.restaurant.user.application.model.TestApplicationUser.TEST_USER_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class LoggedUserDetailsQueryHandlerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ContextHolder contextHolder;

    @Spy
    private ToUserViewConverter toUserViewConverter;

    @InjectMocks
    private LoggedUserDetailsQueryHandler loggedUserDetailsQueryHandler;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testHandle() {

        //given
        LoggedUserDetailsQuery loggedUserDetailsQuery = LoggedUserDetailsQuery.of();

        TestApplicationUser testApplicationUser = new TestApplicationUser();
        String userId = TEST_USER_ID;

        //when
        when(contextHolder.getContext())
                .thenReturn(UserContext.of(Option.of(userId)));
        when(userRepository.findByUserId(userId))
                .thenReturn(Option.of(testApplicationUser));

        Option<UserDetailsView> userDetailsView = loggedUserDetailsQueryHandler.handle(loggedUserDetailsQuery);

        //then
        verify(userRepository).findByUserId(userId);
        verifyNoMoreInteractions(userRepository);

        assertThat(userDetailsView).isNotNull();

        UserDetailsView actual = userDetailsView.get();
        assertThat(actual).isNotNull();
        assertThat(actual.getName()).isEqualTo(testApplicationUser.getRestaurantClient().getName());
    }
}