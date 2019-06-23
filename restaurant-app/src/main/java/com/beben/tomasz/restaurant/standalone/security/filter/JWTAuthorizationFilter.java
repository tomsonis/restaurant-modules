package com.beben.tomasz.restaurant.standalone.security.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.beben.tomasz.restaurant.commons.ContextHolder;
import com.beben.tomasz.restaurant.commons.EmptyContext;
import com.beben.tomasz.restaurant.commons.UserContext;
import com.beben.tomasz.restaurant.standalone.security.JwtTokenUtil;
import com.beben.tomasz.restaurant.standalone.security.user.LoggedUser;
import io.vavr.control.Option;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static com.beben.tomasz.restaurant.standalone.security.configuration.SecurityConstants.HEADER_STRING;
import static com.beben.tomasz.restaurant.standalone.security.configuration.SecurityConstants.TOKEN_PREFIX;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private ContextHolder contextHolder;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, ContextHolder contextHolder) {
        super(authenticationManager);
        this.contextHolder = contextHolder;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain chain
    ) throws IOException, ServletException {

        String header = request.getHeader(HEADER_STRING);
        if (Objects.nonNull(header) && header.startsWith(TOKEN_PREFIX)) {
            String token = request.getHeader(HEADER_STRING);
            DecodedJWT decodedJWT = JwtTokenUtil.decodeToken(token);

            LoggedUser loggedUser = JwtTokenUtil.decodeLoggedUser(decodedJWT);
            List<SimpleGrantedAuthority> roles = JwtTokenUtil.decodeRoles(decodedJWT);

            Authentication authentication = authenticate(loggedUser, roles);
            if (Objects.nonNull(authentication)) {
                contextHolder.setContext(UserContext.of(Option.of(loggedUser.getId())));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } else {
            contextHolder.setContext(EmptyContext.newInstance());
        }

        chain.doFilter(request, response);
    }

    private Authentication authenticate(LoggedUser loggedUser, List<SimpleGrantedAuthority> roles) {
        if (Objects.nonNull(loggedUser)) {
            return new UsernamePasswordAuthenticationToken(
                    loggedUser.getUsername(),
                    null,
                    roles
            );
        }
        return null;
    }
}
