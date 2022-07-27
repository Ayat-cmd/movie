package com.ayat.springboot.movie_server.exception_handler;

public class MovieIncorrectIdException extends RuntimeException {
    public MovieIncorrectIdException(String message) {
        super(message);
    }
}
