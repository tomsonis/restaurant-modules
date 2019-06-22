package com.beben.tomasz.restaurant.user.api.query;

import com.beben.tomasz.cqrs.api.query.Query;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class UserExistQuery implements Query<Boolean> {

    private String userId;
}
