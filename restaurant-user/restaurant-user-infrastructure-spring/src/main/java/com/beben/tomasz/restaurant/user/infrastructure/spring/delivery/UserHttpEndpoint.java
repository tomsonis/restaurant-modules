package com.beben.tomasz.restaurant.user.infrastructure.spring.delivery;

import com.beben.tomasz.cqrs.api.command.CommandExecutor;
import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import com.beben.tomasz.restaurant.user.api.query.LoggedUserDetailsQuery;
import com.beben.tomasz.restaurant.user.api.view.UserDetailsView;
import com.beben.tomasz.restaurant.user.api.command.RegisterUserCommand;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserHttpEndpoint {

    private CommandExecutor commandExecutor;

    private QueryExecutor queryExecutor;

    @PostMapping("register")
    String registerUser(@Valid @RequestBody RegisterUserCommand registerUserCommand) throws Exception {
        return commandExecutor.execute(registerUserCommand);
    }

    @GetMapping("details")
    Option<UserDetailsView> userDetails() throws Exception {
        LoggedUserDetailsQuery loggedUserDetailsQuery = LoggedUserDetailsQuery.of();
        return queryExecutor.execute(loggedUserDetailsQuery);
    }

}
