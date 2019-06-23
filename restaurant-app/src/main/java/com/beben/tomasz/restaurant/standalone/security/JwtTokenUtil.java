package com.beben.tomasz.restaurant.standalone.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.beben.tomasz.restaurant.standalone.security.user.LoggedUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.beben.tomasz.restaurant.standalone.security.configuration.SecurityConstants.SECRET;
import static com.beben.tomasz.restaurant.standalone.security.configuration.SecurityConstants.TEN_DAYS_IN_MILLIS;
import static com.beben.tomasz.restaurant.standalone.security.configuration.SecurityConstants.TOKEN_PREFIX;

public class JwtTokenUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String createToken(LoggedUser loggedUser, Collection<? extends GrantedAuthority> grantedAuthorities) throws JsonProcessingException {

        String loggedUserInfo = objectMapper
                .writeValueAsString(loggedUser);

        return JWT.create()
                .withSubject(loggedUserInfo)
                .withExpiresAt(new Date(System.currentTimeMillis() + TEN_DAYS_IN_MILLIS))
                .withArrayClaim("roles", collectRoles(grantedAuthorities))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));

    }

    public static DecodedJWT decodeToken(String token) {
        return JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                .build()
                .verify(token.replace(TOKEN_PREFIX, ""));
    }

    public static List<SimpleGrantedAuthority> decodeRoles(DecodedJWT decodedJWT) {
        Claim roles = decodedJWT.getClaim("roles");
        if (roles != null) {
            return roles.asList(SimpleGrantedAuthority.class);
        }
        return Collections.emptyList();
    }

    public static LoggedUser decodeLoggedUser(DecodedJWT decodedJWT) {
        try {
            String subject = decodedJWT.getSubject();
            return new ObjectMapper()
                    .readValue(subject, LoggedUser.class);
        } catch (IOException e) {
            return null;
        }
    }

    private static String[] collectRoles(Collection<? extends GrantedAuthority> grantedAuthorities) {
        return grantedAuthorities.stream()
                .map(GrantedAuthority::getAuthority)
                .toArray(String[]::new);
    }

}
