package com.beben.tomasz.restaurant.user.application;

import com.beben.tomasz.cqrs.api.query.Query;
import com.beben.tomasz.restaurant.user.domain.UserId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class UserIdQuery implements Query<UserId> {

    private String username;
}
