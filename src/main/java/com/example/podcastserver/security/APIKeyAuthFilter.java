/*
package com.example.podcastserver.security;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class APIKeyAuthFilter extends AbstractPreAuthenticatedProcessingFilter {

    public static final ArrayList<Map<String, Object>> clientApisList = new ArrayList<>();

    @Override
    protected HttpServletRequest getPreAuthenticatedPrincipal(HttpServletRequest request) {
        clientApisList.add(Map.of("ip", request.getRemoteAddr(), "api", request.getRequestURI(), "params", request.getParameterMap(), "device", request.getHeader("User-Agent"), "threadId", Thread.currentThread().getId(), "threadStatus", Thread.currentThread().getState(), "created_time", new Date()));
        return request;
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return "N/A";
    }
}

*/
