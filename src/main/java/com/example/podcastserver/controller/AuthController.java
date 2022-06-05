package com.example.podcastserver.controller;

import com.example.podcastserver.model.ErrorsField;
import com.example.podcastserver.payload.Login;
import com.example.podcastserver.payload.Register;
import com.example.podcastserver.payload.Response;
import com.example.podcastserver.payload.Status;
import com.example.podcastserver.service.AuthService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public HttpEntity<?> register(@Valid @ModelAttribute Register register, BindingResult error) {
        if (error.hasErrors())
            return ResponseEntity.status(400).body(new Response(new Status(400, "Bed request"), error.getFieldErrors().stream().map(fieldError -> new ErrorsField(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList())));
        return this.authService.register(register);
    }

    @GetMapping("/username")
    public HttpEntity<?> checkUsername(@RequestParam String username) {
        return ResponseEntity.ok(this.authService.checkUsername(username));
    }

    @PostMapping("/login")
    public HttpEntity<?> register(@Valid @RequestBody Login login, BindingResult error) {
        return !error.hasErrors() ? this.authService.login(login) : ResponseEntity.status(400).body(new Response(new Status(400, "Bed request"), error.getFieldErrors().stream().map(fieldError -> new ErrorsField(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList())));
    }

    @PostMapping("/password/{username}")
    public HttpEntity<?> forgetPassword(@PathVariable String username) {
        return this.authService.forgetPassword(username);
    }

}
