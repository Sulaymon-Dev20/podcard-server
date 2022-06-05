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
//        fileService.saveFile(register.getImage());
        Map<String, Object> maps = jdbcTemplate.queryForMap(register.toString());
        maps.remove("saved");
        maps.remove("downloads");
        maps.remove("fellows");
        maps.remove("history");
        return ResponseEntity.ok(new Response(new Status(200, "ok"), Map.of("token","Bearer YXNmZGFzIGZhc2RmIGFzZiBzYWYgYXNmIFN1bGF5bW9uIFlhaHlvIG5pbWEgZ3BhbGEgYm92b3R0aW1pIG5pbWEgcWl2b3NzaXog")));
    }

    public HttpEntity<?> login(Login login) {
        List<Map<String, Object>> users = jdbcTemplate.queryForList("select * from users where username='" + login.getUsername() + "' and password='" + login.getPassword() + "'");
        return ResponseEntity.ok(new Response(new Status(users.isEmpty() ? 403 : 200), Map.of("token", !users.isEmpty() ? "Bearer YXNmZGFzIGZhc2RmIGFzZiBzYWYgYXNmIFN1bGF5bW9uIFlhaHlvIG5pbWEgZ3BhbGEgYm92b3R0aW1pIG5pbWEgcWl2b3NzaXog" : null)));
    }

    public HttpEntity<?> forgetPassword(String username) {
        return null;
    }

    public Response checkUsername(String username) {
        if (username.length() > 5) {
            boolean empty = jdbcTemplate.queryForList("select * from users u where u.username=?", username).isEmpty();
            return new Response(new Status(200), Map.of("free", !empty, "message", "okokok"));
        } else {
            return new Response(new Status(200), Map.of("free", false, "message", "this username is alrealy used"));
        }
    }
}
