package com.example.podcastserver.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Episodes {
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private MultipartFile file;
}
