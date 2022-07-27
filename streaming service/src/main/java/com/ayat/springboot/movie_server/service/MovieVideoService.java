package com.ayat.springboot.movie_server.service;

import com.ayat.springboot.movie_server.dto.Movie;
import com.ayat.springboot.movie_server.entity.MovieEntity;

import java.util.List;

public interface MovieVideoService {
    public void findVideoToMovie(List<Movie> movies);

    public void saveVideo(String video, int idMovie);
}
