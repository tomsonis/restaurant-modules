package com.beben.tomasz.restaurant.user.application.query;

import com.beben.tomasz.cqrs.api.query.QueryHandler;
import com.beben.tomasz.restaurant.user.api.query.UserExistQuery;
import com.beben.tomasz.restaurant.user.domain.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserExistQueryHandler implements QueryHandler<UserExistQuery, Boolean> {

    private UserRepository userRepository;

    @Override
    public Boolean handle(UserExistQuery userExistQuery) {
        return userRepository.existUser(userExistQuery.getUserId());
    }
}
