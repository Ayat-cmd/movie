package com.ayat.springboot.movie_server.dto;

import com.ayat.springboot.movie_server.entity.MovieCommentaryEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MovieCommentary {
    private int id;
    private String commentary;
    private String movie;
    private String user;

    public static MovieCommentary toMovieCommentary(MovieCommentaryEntity entity) {
        MovieCommentary model = new MovieCommentary();
        model.setId(entity.getId());
        model.setCommentary(entity.getCommentary());
        model.setMovie(entity.getMovie().getTitle_ru());
        model.setUser(entity.getUser().getUsername());
        return model;
    }

    public static List<MovieCommentary> toMovieCommentaries(List<MovieCommentaryEntity> movieCommentaryEntities) {
        List<MovieCommentary> modelMovieCommentaries = new ArrayList<>(movieCommentaryEntities.size());
        for (MovieCommentaryEntity entity : movieCommentaryEntities) {
            modelMovieCommentaries.add(toMovieCommentary(entity));
        }
        return modelMovieCommentaries;
    }
}
