package com.beben.tomasz.restaurant.user.api.command;

import com.beben.tomasz.cqrs.api.command.Command;
import com.beben.tomasz.restaurant.user.api.ClientRequest;
import com.beben.tomasz.restaurant.user.api.view.RoleTypeView;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class RegisterUserCommand implements Command<String> {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Email
    private String email;

    private ClientRequest client;

    private String restaurantReference;

    private Collection<RoleTypeView> restaurantRoleTypes;

}
