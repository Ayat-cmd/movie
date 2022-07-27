package com.ayat.springboot.movie_server.dto;

import com.ayat.springboot.movie_server.entity.MovieEntity;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Movie {
    private int id;
    private int kp_id;
    private String imdbId;
    private String titleRu;
    private String titleEn;
    private String poster;
    private String country;
    private String genre;
    private String actors;
    private String producers;
    private String premiere;
    private String description;
    private String ratings;
    private List<MovieCommentary> movieCommentaries;

    public static Movie toMovie(MovieEntity entity) {
        Movie model = new Movie();
        model.setId(entity.getId());
        model.setImdbId(entity.getImdb_id());
        model.setTitleRu(entity.getTitle_ru());
        model.setTitleEn(entity.getTitle_en());
        model.setPoster(entity.getPoster());
        model.setCountry(entity.getCountry());
        model.setGenre(entity.getGenre());
        model.setActors(entity.getActors());
        model.setProducers(entity.getProducers());
        model.setPremiere(entity.getPremiere());
        model.setDescription(entity.getDescription());
        model.setRatings(entity.getRatings());
        model.setMovieCommentaries(entity.getMovieCommentaries()
                .stream().map(MovieCommentary::toMovieCommentary)
                .collect(Collectors.toList()));
        return model;
    }

    public static List<Movie> toMovies(List<MovieEntity> movieEntities) {
        List<Movie> modelMovies = new ArrayList<>(movieEntities.size());
        for(MovieEntity entity : movieEntities) {
            modelMovies.add(toMovie(entity));
        }
        return modelMovies;
    }
}
