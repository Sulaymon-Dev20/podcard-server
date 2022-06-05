package com.example.podcastserver.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Register {
    @NotNull
    private String username;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Length(min = 8)
    private String password;

    @NotNull(message = "file not be null")
    private MultipartFile image;

    @Override
    public String toString() {
        return "insert into users (username, mail, password, img_id) VALUES ('" + this.username + "','" + this.email + "','" + this.password + "','" + image.getOriginalFilename() + "') returning *";
    }
}
