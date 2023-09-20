package com.cob.salesforce.security;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UUIDUtils {
    public static String get() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated())
            return authentication.getName();
        return "";
    }
}