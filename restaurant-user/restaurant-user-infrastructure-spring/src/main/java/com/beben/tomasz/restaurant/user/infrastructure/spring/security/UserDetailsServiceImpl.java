package com.beben.tomasz.restaurant.user.infrastructure.spring.security;


import com.beben.tomasz.restaurant.user.domain.ApplicationUser;
import com.beben.tomasz.restaurant.user.domain.RestaurantUser;
import com.beben.tomasz.restaurant.user.domain.UserNotFoundException;
import com.beben.tomasz.restaurant.user.domain.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            ApplicationUser user = userRepository.findByUsername(username);

            return new User(
                    user.getUsername(),
                    user.getPassword(),
                    mapRole(user.getRestaurantUser())
            );
        } catch (UserNotFoundException e) {
            throw new UsernameNotFoundException(username);
        }
    }

    private Set<GrantedAuthority> mapRole(RestaurantUser restaurantUser) {
        if (Objects.isNull(restaurantUser)) {
            return Collections.emptySet();
        }

        return CollectionUtils.emptyIfNull(restaurantUser.getRestaurantRoleTypes()).stream()
                .map(roleType -> new SimpleGrantedAuthority(roleType.name()))
                .collect(Collectors.toSet());
    }
}
