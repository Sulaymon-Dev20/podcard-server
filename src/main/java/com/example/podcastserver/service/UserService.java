package com.example.podcastserver.service;

import com.example.podcastserver.payload.Response;
import com.example.podcastserver.payload.Status;
import com.example.podcastserver.payload.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.json.JSONArray;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {

    final JdbcTemplate jdbcTemplate;
    final FileService fileService;

    public UserService(JdbcTemplate jdbcTemplate, FileService fileService) {
        this.jdbcTemplate = jdbcTemplate;
        this.fileService = fileService;
    }

    public Response update(User user) {
        fileService.saveFile(user.getImage());
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("update users set mail=?,img_id=? where username=? returning *", user.getEmail(), user.getImage().getOriginalFilename(), user.getUsername());
        return new Response(new Status(200, "updated"));
    }

    @SneakyThrows
    public Response fallow(Long chartId) {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from users where id=?", 1);
        List<Object> objects = new JSONArray((String) maps.get(0).get("fellows")).toList();
        objects.add(chartId);
        String json = new ObjectMapper().writeValueAsString(objects);
        jdbcTemplate.update("update users set fellows=? where id=? returning *", json, 1);
        return null;
    }

    @SneakyThrows
    public Response unfallow(Long chartId) {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from users where id=?", 1);
        List<Object> objects = new JSONArray((String) maps.get(0).get("fellows")).toList().stream().filter(item -> item.hashCode() != chartId.hashCode()).collect(Collectors.toList());
        String json = new ObjectMapper().writeValueAsString(objects);
        jdbcTemplate.update("update users set fellows=? where id=? returning *", json, 1);
        return null;
    }
}
