package com.beben.tomasz.restaurant.user.infrastructure.spring.persistance.user;

import com.beben.tomasz.restaurant.user.domain.*;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@Transactional
@AllArgsConstructor
public class JpaUserRepository implements UserRepository {

    private EntityManager entityManager;

    @Override
    public void save(ApplicationUser applicationUser) {
        entityManager.persist(applicationUser);
    }

    @Override
    public void save(AuthenticateUser authenticateUser) {
        entityManager.persist(authenticateUser);
    }

    @Override
    public ApplicationUser findByUsername(String username) throws UserNotFoundException {
        return readUser(username);
    }

    @Override
    public AuthenticateUser readUserToAuthenticate(String userId) {
        return entityManager.find(UserEntity.class, userId);
    }

    @Override
    public boolean existUserName(String username) {
        return !readUserByUsername(username).isEmpty();
    }

    @Override
    public boolean existEmail(String email) {
        return !readUserByEmail(email).isEmpty();
    }

    @Override
    public boolean existUser(String userId) {
        List<UserEntity> users = entityManager.createQuery(
                "select u from UserEntity u where u.id= :userId",
                UserEntity.class
        )
                .setParameter("userId", userId)
                .getResultList();

        return !users.isEmpty();
    }

    @Override
    public UserId generateId() {
        return UserId.of(
                UUID.randomUUID().toString()
        );
    }

    @Override
    public Option<ApplicationUser> findByUserId(String userId) {
        return Option.of(entityManager.find(UserEntity.class, userId));
    }

    private AuthenticateUser readUser(String username) throws UserNotFoundException {
        List<UserEntity> userEntities = readUserByUsername(username);

        if (userEntities.isEmpty()) {
            throw new UserNotFoundException(String.format("User with username '%s' not exist.", username));
        } else {
            return userEntities.get(0);
        }
    }

    private List<UserEntity> readUserByUsername(String username) {
        return entityManager.createQuery(
                "select u from UserEntity u where u.username = :username",
                UserEntity.class
        )
                .setParameter("username", username)
                .getResultList();
    }

    private List<UserEntity> readUserByEmail(String email) {
        return entityManager.createQuery(
                "select u from UserEntity u where u.email = :email",
                UserEntity.class
        )
                .setParameter("email", email)
                .getResultList();
    }
}
