package com.example.podcastserver.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chart {
    @NotNull
    private String name;
    @NotNull
    @Length(min = 12)
    private String description;
    private String category;
    private MultipartFile image;
}
