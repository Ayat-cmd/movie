package com.ayat.springboot.movie_server.dto;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String email;
    private String password;
}
