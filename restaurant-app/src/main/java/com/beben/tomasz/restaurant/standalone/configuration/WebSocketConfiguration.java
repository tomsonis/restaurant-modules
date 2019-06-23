package com.beben.tomasz.restaurant.standalone.configuration;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.beben.tomasz.restaurant.standalone.security.JwtTokenUtil;
import com.beben.tomasz.restaurant.user.domain.RestaurantRoleType;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import javax.security.auth.message.AuthException;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry.addEndpoint("/socket")
                .setAllowedOrigins("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app")
                .enableSimpleBroker("/new-order", "/order-status-change");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new DefaultChannelInterceptor());
    }

    class DefaultChannelInterceptor implements ChannelInterceptor {
        @Override
        public Message<?> preSend(Message<?> message, MessageChannel channel) {
            StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
            List<String> tokenList = accessor.getNativeHeader("Authorization");

            if(tokenList == null || tokenList.isEmpty()) {
                return new ErrorMessage(new AuthException("Unauthorized."));
            }

            DecodedJWT decodedJWT = JwtTokenUtil.decodeToken(tokenList.get(0));
            List<SimpleGrantedAuthority> roles = JwtTokenUtil.decodeRoles(decodedJWT);

            boolean anyMatch = roles.stream()
                    .anyMatch(simpleGrantedAuthority ->
                            roleTypes().contains(RestaurantRoleType.valueOf(simpleGrantedAuthority.getAuthority()))
                    );

            if (!anyMatch) {
                return new ErrorMessage(new AuthException("Unauthorized."));

            }

            return MessageBuilder.createMessage(message.getPayload(), accessor.getMessageHeaders());
        }

        private List<RestaurantRoleType> roleTypes() {
            return Arrays.asList(
                    RestaurantRoleType.ROLE_WAITER, RestaurantRoleType.ROLE_COOK, RestaurantRoleType.ROLE_MANAGER, RestaurantRoleType.ROLE_ADMIN
            );
        }
    }
}
