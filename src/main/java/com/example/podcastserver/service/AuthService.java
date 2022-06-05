package com.example.podcastserver.service;

import com.example.podcastserver.payload.Login;
import com.example.podcastserver.payload.Register;
import com.example.podcastserver.payload.Response;
import com.example.podcastserver.payload.Status;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AuthService {

    final JdbcTemplate jdbcTemplate;
    final FileService fileService;

    public AuthService(JdbcTemplate jdbcTemplate, FileService fileService) {
        this.jdbcTemplate = jdbcTemplate;
        this.fileService = fileService;
    }

    public HttpEntity<?> register(Register register) {
        fileService.saveFile(register.getImage());
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(register.toString());
        return ResponseEntity.ok(new Response(new Status(200, "ok"), maps.get(0)));
    }

    public HttpEntity<?> login(Login login) {
        List<Map<String, Object>> users = jdbcTemplate.queryForList(login.toString());
        return ResponseEntity.ok(new Response(new Status(users.isEmpty() ? 403 : 200), users.isEmpty() ? null : users.get(0)));
    }

    public HttpEntity<?> forgetPassword(String username) {
        return null;
    }

    public Response checkUsername(String username) {
        if (username.length() > 5) {
            return new Response(new Status(200, jdbcTemplate.queryForList("select * from users u where u.username=?", username).isEmpty() ? "this is free" : "this username is alrealy used"));
        } else {
            return new Response(new Status(200, "this username is alrealy used"));
        }
    }
}
