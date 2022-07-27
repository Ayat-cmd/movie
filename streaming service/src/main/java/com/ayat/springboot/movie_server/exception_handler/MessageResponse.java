package com.ayat.springboot.movie_server.exception_handler;

import lombok.Data;

@Data
public class MessageResponse{
    String message;

    public MessageResponse(String message) {
        this.message = message;
    }
}
