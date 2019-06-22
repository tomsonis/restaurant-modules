package com.beben.tomasz.restaurant.user.api.command;

import com.beben.tomasz.cqrs.api.command.Command;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class SaveTokenCommand implements Command<Void> {

    private String username;

    private String token;
}
