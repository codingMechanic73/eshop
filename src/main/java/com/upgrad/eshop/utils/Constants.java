package com.upgrad.eshop.utils;

public final class Constants {
    private Constants() {
    }

    public static final class Roles {
        private Roles() {
        }

        public static final String USER = "USER";
        public static final String ADMIN = "ADMIN";
    }

    public static final class JwtToken {
        private JwtToken() {
        }

        public static final String HEADER_KEY = "Authorization";
        public static final String JWT_TOKEN_PREFIX = "Bearer ";
        public static final long JWT_TOKEN_VALIDITY = 1000 * 60 * 10; // 10 minutes
        public static final String AUTHORITY_KEY = "scopes";
    }

    public static final class RegexPattern {
        private RegexPattern() {

        }

        public static final String ZIPCODE = "^[0-9][0-9]{4}[0-9]$";
        public static final String PHONE = "^[0-9][0-9]{8}[0-9]$";
        public static final String EMAIL = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-z]{2,6}$";
    }
}
