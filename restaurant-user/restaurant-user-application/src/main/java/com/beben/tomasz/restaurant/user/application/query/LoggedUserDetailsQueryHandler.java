package com.beben.tomasz.restaurant.user.application.query;

import com.beben.tomasz.cqrs.api.query.QueryHandler;
import com.beben.tomasz.restaurant.commons.ContextHolder;
import com.beben.tomasz.restaurant.user.api.query.LoggedUserDetailsQuery;
import com.beben.tomasz.restaurant.user.api.view.UserDetailsView;
import com.beben.tomasz.restaurant.user.application.converters.ToUserViewConverter;
import com.beben.tomasz.restaurant.user.domain.UserRepository;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoggedUserDetailsQueryHandler implements QueryHandler<LoggedUserDetailsQuery, Option<UserDetailsView>> {

    private UserRepository userRepository;

    private ContextHolder contextHolder;

    private ToUserViewConverter toUserViewConverter;

    @Override
    public Option<UserDetailsView> handle(LoggedUserDetailsQuery loggedUserDetailsQuery) {
        return contextHolder.getContext().getUserId()
                .flatMap(userRepository::findByUserId)
                .map(toUserViewConverter::convert);
    }
}
