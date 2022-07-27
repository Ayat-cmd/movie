package com.ayat.springboot.movie_server.service.impl;

import com.ayat.springboot.movie_server.dao.MovieRepository;
//import com.ayat.springboot.movie_server.dao.MovieVideoRepository;
import com.ayat.springboot.movie_server.dao.MovieVideoRepository;
import com.ayat.springboot.movie_server.entity.MovieEntity;
import com.ayat.springboot.movie_server.dto.Movie;
import com.ayat.springboot.movie_server.entity.MovieVideoEntity;
import com.ayat.springboot.movie_server.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MoviesServiceImp implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    @Transactional
    public void saveAll(List<MovieEntity> movies) {
        movieRepository.saveAll(movies);
    }

    @Override
    @Transactional
    public List<Movie> getAllMovies() {
        return Movie.toMovies(movieRepository.findAll());
    }

    @Override
    public Movie getMovie(int id) {
        MovieEntity movie = movieRepository.findById(id).get();
        return Movie.toMovie(movie);
    }

    @Override
    public void deleteById(int id) {
        movieRepository.deleteById(id);
    }
}
