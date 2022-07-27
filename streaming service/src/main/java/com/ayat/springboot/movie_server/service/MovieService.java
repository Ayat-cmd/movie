package com.ayat.springboot.movie_server.service;

import com.ayat.springboot.movie_server.dto.Movie;
import com.ayat.springboot.movie_server.entity.MovieEntity;

import java.util.List;

public interface MovieService {
    public void saveAll(List<MovieEntity> movies);

    public List<Movie> getAllMovies();

    public Movie getMovie(int id);

    public void deleteById(int id);
}
