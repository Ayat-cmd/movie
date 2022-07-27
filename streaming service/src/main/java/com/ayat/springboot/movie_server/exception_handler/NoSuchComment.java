package com.ayat.springboot.movie_server.exception_handler;

public class NoSuchComment extends RuntimeException{
    public NoSuchComment(String message) {
        super(message);
    }
}
