package com.beben.tomasz.restaurant.user.api.query;

import com.beben.tomasz.cqrs.api.query.Query;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class UserExistQuery implements Query<Boolean> {

    private String userId;
}
