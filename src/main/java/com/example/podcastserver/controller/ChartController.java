package com.example.podcastserver.controller;

import com.example.podcastserver.model.ErrorsField;
import com.example.podcastserver.payload.*;
import com.example.podcastserver.service.ChartService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/playList")
public class ChartController {
    final ChartService service;

    public ChartController(ChartService service) {
        this.service = service;
    }

    @PostMapping
    public Response saveOrEdit(@Valid @ModelAttribute Chart chart, BindingResult error) {
        return !error.hasErrors() ? this.service.saveOrEdit(chart) : new Response(new Status(400, "Bed request"), error.getFieldErrors().stream().map(fieldError -> new ErrorsField(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList()));
    }

    @PostMapping("/{id}")
    public Response saveOrEditEpisodes(@Valid @ModelAttribute Episodes episodes, BindingResult error, @PathVariable String id) {
        return !error.hasErrors() ? this.service.saveOrEditEp(episodes, id) : new Response(new Status(400, "Bed request"), error.getFieldErrors().stream().map(fieldError -> new ErrorsField(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList()));
    }

    @GetMapping("/")
    public Response getAll() {
        return this.service.findAll();
    }

    @GetMapping("/{id}")
    public Response getById(@PathVariable String id) {
        return this.service.getById(id);
    }

    @GetMapping("/views")
    public Response getByViews() {
        return this.service.findByViews();
    }

    @GetMapping("/feedback")
    public Response getByFeedback() {
        return this.service.findByFeedback();
    }

    @GetMapping("/feedback/{id}")
    public Response getByFeedbackId(@PathVariable String id, @RequestParam Integer ray) {
        return this.service.findByFeedbackId(id, ray);
    }

    @PutMapping("/{id}")
    public Response commentChart(@RequestBody CommentChart commentChart, @PathVariable String id) {
        return this.service.commentChart(commentChart, id);
    }

    @DeleteMapping("/{id}")
    public Response banChart(@PathVariable("id") String chartId, @RequestParam String message) {
        return this.service.banChart(chartId, message);
    }
}
