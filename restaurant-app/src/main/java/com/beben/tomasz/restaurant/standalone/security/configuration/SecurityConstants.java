package com.beben.tomasz.restaurant.standalone.security.configuration;

public class SecurityConstants {

    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long TEN_DAYS_IN_MILLIS = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/user/register";
}
