package com.example.podcastserver.security;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UsersFilter {

    final JdbcTemplate jdbcTemplate;

    public UsersFilter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean checkAuth(HttpServletRequest request) {
        try {
            if (!request.getRequestURI().startsWith("/error")) {
                if (request.getRequestURI().contains("/api/scoreboard")) {
                } else {
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
