package com.example.podcastserver.controller;

import com.example.podcastserver.model.ErrorsField;
import com.example.podcastserver.payload.Response;
import com.example.podcastserver.payload.Status;
import com.example.podcastserver.payload.User;
import com.example.podcastserver.service.UserService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {

    final UserService service;

    public UserController(UserService userService) {
        this.service = userService;
    }

    @PostMapping("/update")
    public Response update(@Valid @ModelAttribute User user, BindingResult error) {
        return !error.hasErrors() ? this.service.update(user) : new Response(new Status(400, "Bed request"), error.getFieldErrors().stream().map(fieldError -> new ErrorsField(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList()));
    }

    @PostMapping("/me")
    public Response sendMe() {
        return this.service.sendMe();
    }

    @PostMapping("/fallow/{chartId}")
    public Response fallow(@PathVariable Long chartId) {
        return this.service.fallow(chartId);
    }

    @PostMapping("/unFallow/{chartId}")
    public Response unFallow(@PathVariable Long chartId) {
        return this.service.unfallow(chartId);
    }
}
