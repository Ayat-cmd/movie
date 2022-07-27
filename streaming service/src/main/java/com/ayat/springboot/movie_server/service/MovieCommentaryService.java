package com.ayat.springboot.movie_server.service;

import com.ayat.springboot.movie_server.entity.MovieCommentaryEntity;
import com.ayat.springboot.movie_server.dto.MovieCommentary;

import java.util.List;

public interface MovieCommentaryService {

    public MovieCommentary save(MovieCommentaryEntity movieCommentary, int id, String email);

    public List<MovieCommentary> getAllCommitsWithMovies();

    public List<MovieCommentary> getCommit(int id);

    public void deleteById(int id);
}
