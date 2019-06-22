package com.beben.tomasz.restaurant.user.api.query;

import com.beben.tomasz.cqrs.api.query.Query;
import com.beben.tomasz.restaurant.user.api.view.UserDetailsView;
import io.vavr.control.Option;
import lombok.NoArgsConstructor;


@NoArgsConstructor(staticName = "of")
public class LoggedUserDetailsQuery implements Query<Option<UserDetailsView>> {
}
