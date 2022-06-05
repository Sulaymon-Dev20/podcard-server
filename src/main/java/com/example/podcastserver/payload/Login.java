package com.example.podcastserver.payload;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class Login {
    @NotNull
    private String username;
    @NotNull
    @Length(min = 8)
    private String password;

    @Override
    public String toString() {
        return "select * from users u where username=" + this.username + " and password=" + this.password;
    }
}
