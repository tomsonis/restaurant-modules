package com.beben.tomasz.restaurant.user.domain;

import io.vavr.control.Option;

public interface UserRepository {

    void save(ApplicationUser applicationUser);

    void save(AuthenticateUser authenticateUser);

    ApplicationUser findByUsername(String username) throws UserNotFoundException;

    AuthenticateUser readUserToAuthenticate(String username) throws UserNotFoundException;

    boolean existUserName(String username);

    boolean existEmail(String email);

    boolean existUser(String userId);

    UserId generateId();

    Option<ApplicationUser> findByUserId(String userId);
}
