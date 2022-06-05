package com.example.podcastserver.service;

import com.example.podcastserver.payload.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.json.JSONArray;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ChartService {
    final JdbcTemplate jdbcTemplate;
    final FileService fileService;

    final List<String> category = List.of("Test");

    public ChartService(JdbcTemplate jdbcTemplate, FileService fileService) {
        this.jdbcTemplate = jdbcTemplate;
        this.fileService = fileService;
    }


    public Response saveOrEdit(Chart chart) {
        fileService.saveFile(chart.getImage());
        int i = category.indexOf(chart.getCategory());
        Map<String, Object> map = jdbcTemplate.queryForMap("insert into charts (name, description, img_id, category, create_at) values (?,?,?,?,?) returning *", chart.getName(), chart.getDescription(), chart.getImage().getOriginalFilename(), i, 1);
        map.remove("episodes");
        map.remove("comments");
        map.remove("feedback");
        map.remove("band_count");
        return new Response(new Status(200, "created"), map);
    }

    public Response findAll() {
        return new Response(new Status(200), jdbcTemplate.queryForList("select * from charts;").stream().peek(item -> {
            item.put("episodes", new JSONArray((String) item.get("episodes")).toList());
            item.put("comments", new JSONArray((String) item.get("comments")).toList());
            item.put("feedback", new JSONArray((String) item.get("feedback")).toList());
            item.remove("band_count");
        }));
    }

    public Response findByViews() {
        return new Response(new Status(200), jdbcTemplate.queryForList("select * from charts order by watched_count desc").stream().peek(item -> {
            item.put("episodes", new JSONArray((String) item.get("episodes")).toList());
            item.put("comments", new JSONArray((String) item.get("comments")).toList());
            item.put("feedback", new JSONArray((String) item.get("feedback")).toList());
            item.put("band_count", new JSONArray((String) item.get("band_count")).toList());
        }));
    }

    public Response findByFeedbackId(String id, Integer ray) {
        return new Response(new Status(200), jdbcTemplate.queryForList("select * from charts").stream().peek(item -> {
            item.put("episodes", new JSONArray((String) item.get("episodes")).toList());
            List<Object> objects = new JSONArray((String) item.get("comments")).toList();
            objects.add(Map.of("userId", 1, "feedback", ray));
            item.put("comments", objects);
            item.put("feedback", new JSONArray((String) item.get("feedback")).toList());
            item.put("band_count", new JSONArray((String) item.get("band_count")).toList());
        }));
    }

    public Response findByFeedback() {
        return new Response(new Status(200), jdbcTemplate.queryForList("select * from charts").stream().peek(item -> {
            item.put("episodes", new JSONArray((String) item.get("episodes")).toList());
            item.put("comments", new JSONArray((String) item.get("comments")).toList());
            item.put("feedback", new JSONArray((String) item.get("feedback")).toList());
            item.put("band_count", new JSONArray((String) item.get("band_count")).toList());
        }).sorted());
    }

    public Response commentChart(CommentChart commentChart) {
        return new Response(new Status(200), jdbcTemplate.queryForList("select * from charts").stream().peek(item -> {
            item.put("episodes", new JSONArray((String) item.get("episodes")).toList());
            item.put("comments", new JSONArray((String) item.get("comments")).toList());
            item.put("feedback", new JSONArray((String) item.get("feedback")).toList());
            item.put("band_count", new JSONArray((String) item.get("band_count")).toList());
        }).sorted());
    }

    public Response banChart(String chartId, String message) {
        return null;
    }

    public Response getById(String id) {
        Map<String, Object> item = jdbcTemplate.queryForMap("update charts set watched_count=watched_count+1 where id=" + id + " returning *");
        item.put("episodes", new JSONArray((String) item.get("episodes")).toList());
        item.put("comments", new JSONArray((String) item.get("comments")).toList());
        item.put("feedback", new JSONArray((String) item.get("feedback")).toList());
        item.put("band_count", new JSONArray((String) item.get("band_count")).toList());
        return new Response(new Status(200), item);
    }

    @SneakyThrows
    public Response saveOrEditEp(Episodes episodes, String id) {
        String s = fileService.saveFile(episodes.getFile());
        Map<String, Object> name = Map.of("name", episodes.getName(), "description", episodes.getDescription(), "file", Objects.requireNonNull(episodes.getFile().getOriginalFilename()));
        Map<String, Object> map = jdbcTemplate.queryForMap("select * from charts where id=" + id);
        List<Object> objects = new JSONArray((String) map.get("episodes")).toList();
        objects.add(name);
        map.put("episodes", objects);
        jdbcTemplate.queryForMap("update charts set name=?,description=?,episodes=? where id=" + id + " returning *", episodes.getName(), episodes.getDescription(), new ObjectMapper().writeValueAsString(objects));
        return new Response(new Status(200));
    }
}
