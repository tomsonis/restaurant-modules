package com.beben.tomasz.restaurant.user.application.command;

import com.beben.tomasz.cqrs.api.command.CommandHandler;
import com.beben.tomasz.restaurant.user.api.ClientRequest;
import com.beben.tomasz.restaurant.user.api.command.RegisterUserCommand;
import com.beben.tomasz.restaurant.user.api.view.RoleTypeView;
import com.beben.tomasz.restaurant.user.domain.ApplicationUser;
import com.beben.tomasz.restaurant.user.domain.ClientFactory;
import com.beben.tomasz.restaurant.user.domain.RestaurantClient;
import com.beben.tomasz.restaurant.user.domain.RestaurantRoleType;
import com.beben.tomasz.restaurant.user.domain.RestaurantUserFactory;
import com.beben.tomasz.restaurant.user.domain.UserFactory;
import com.beben.tomasz.restaurant.user.domain.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
public class RegisterUserCommandHandler implements CommandHandler<RegisterUserCommand, String> {

    private UserRepository userRepository;

    private UserFactory userFactory;

    private ClientFactory clientFactory;

    private RestaurantUserFactory restaurantUserFactory;

    @Override
    public String handle(RegisterUserCommand registerUserCommand) throws Exception {

        boolean existByUsername = userRepository.existUserName(registerUserCommand.getUsername());
        boolean existByEmail = userRepository.existEmail(registerUserCommand.getEmail());

        if (existByUsername) {
            throw new Exception(String.format("Username %s is busy.", registerUserCommand.getUsername()));
        }

        if (existByEmail) {
            throw new Exception(String.format("Email %s is busy.", registerUserCommand.getEmail()));
        }

        RestaurantClient restaurantClient = createClient(registerUserCommand);

        ApplicationUser user = userFactory.createUser(
                registerUserCommand.getUsername(),
                registerUserCommand.getPassword(),
                registerUserCommand.getEmail(),
                restaurantClient,
                restaurantUserFactory.createUser(
                        registerUserCommand.getRestaurantReference(),
                        mapRole(registerUserCommand.getRestaurantRoleTypes())
                )
        );

        userRepository.save(user);

        return user.getUserId().getId();
    }

    private Collection<RestaurantRoleType> mapRole(Collection<RoleTypeView> roleTypeViews) {
        return CollectionUtils.emptyIfNull(roleTypeViews)
                .stream()
                .map(roleTypeView -> RestaurantRoleType.valueOf(roleTypeView.name()))
                .collect(Collectors.toList());
    }

    private RestaurantClient createClient(RegisterUserCommand registerUserCommand) {
        ClientRequest client = registerUserCommand.getClient();
        if (Objects.isNull(client)) {
            return null;
        } else {
            return clientFactory.createClient(
                    client.getName(),
                    client.getSurname(),
                    client.getPhoneNumber()
            );
        }
    }
}
