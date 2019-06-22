package com.beben.tomasz.restaurant.user.application.query;

import com.beben.tomasz.cqrs.api.query.QueryHandler;
import com.beben.tomasz.restaurant.user.application.UserIdQuery;
import com.beben.tomasz.restaurant.user.domain.ApplicationUser;
import com.beben.tomasz.restaurant.user.domain.UserId;
import com.beben.tomasz.restaurant.user.domain.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserIdQueryHandler implements QueryHandler<UserIdQuery, UserId> {

    private UserRepository userRepository;

    @Override
    public UserId handle(UserIdQuery userIdQuery) throws Exception {
        ApplicationUser byUsername = userRepository.findByUsername(userIdQuery.getUsername());
        return byUsername.getUserId();
    }
}
