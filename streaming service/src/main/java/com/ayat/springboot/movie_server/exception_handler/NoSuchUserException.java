package com.ayat.springboot.movie_server.exception_handler;

public class NoSuchUserException extends RuntimeException {
    public NoSuchUserException(String message) {
        super(message);
    }
}
