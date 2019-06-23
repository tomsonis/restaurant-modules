package com.beben.tomasz.restaurant.standalone.security.filter;

import com.beben.tomasz.cqrs.api.query.QueryExecutor;
import com.beben.tomasz.restaurant.standalone.security.JwtTokenUtil;
import com.beben.tomasz.restaurant.standalone.security.user.LoggedUser;
import com.beben.tomasz.restaurant.standalone.security.user.LoginUser;
import com.beben.tomasz.restaurant.user.application.UserIdQuery;
import com.beben.tomasz.restaurant.user.domain.UserId;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.beben.tomasz.restaurant.standalone.security.configuration.SecurityConstants.HEADER_STRING;
import static com.beben.tomasz.restaurant.standalone.security.configuration.SecurityConstants.TOKEN_PREFIX;

@AllArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private QueryExecutor queryExecutor;

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws AuthenticationException {
        try {
            LoginUser loginUser = new ObjectMapper()
                    .readValue(request.getInputStream(), LoginUser.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginUser.getUsername(),
                            loginUser.getPassword()
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult
    ) throws IOException {
        try {
            String username = ((User) authResult.getPrincipal()).getUsername();
            UserId userId = queryExecutor.execute(UserIdQuery.of(username));

            LoggedUser loggedUser = LoggedUser.of(
                    userId.getId(),
                    username
            );

            String token = JwtTokenUtil.createToken(loggedUser, authResult.getAuthorities());

            response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(404);
        }
    }
}
