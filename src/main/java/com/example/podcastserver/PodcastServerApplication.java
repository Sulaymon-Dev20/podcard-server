package com.example.podcastserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

//bizga server kerak bo`ladi postgresql uchun
@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class PodcastServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(PodcastServerApplication.class, args);
    }
}
