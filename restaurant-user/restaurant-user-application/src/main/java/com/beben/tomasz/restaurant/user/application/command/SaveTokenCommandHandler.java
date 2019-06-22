package com.beben.tomasz.restaurant.user.application.command;

import com.beben.tomasz.cqrs.api.command.CommandHandler;
import com.beben.tomasz.restaurant.user.api.command.SaveTokenCommand;
import com.beben.tomasz.restaurant.user.domain.AuthenticateUser;
import com.beben.tomasz.restaurant.user.domain.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SaveTokenCommandHandler implements CommandHandler<SaveTokenCommand, Void> {

    private UserRepository userRepository;

    @Override
    public Void handle(SaveTokenCommand saveTokenCommand) throws Exception {

        AuthenticateUser authenticateUser = userRepository.readUserToAuthenticate(saveTokenCommand.getUsername());
        authenticateUser.authenticate(saveTokenCommand.getToken());

        userRepository.save(authenticateUser);

        return null;
    }
}
