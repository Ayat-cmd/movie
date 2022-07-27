package com.ayat.springboot.movie_server.service.impl;

import com.ayat.springboot.movie_server.dao.MovieCommentaryRepository;
import com.ayat.springboot.movie_server.dao.MovieRepository;
import com.ayat.springboot.movie_server.dao.UserRepository;
import com.ayat.springboot.movie_server.entity.MovieCommentaryEntity;
import com.ayat.springboot.movie_server.entity.MovieEntity;
import com.ayat.springboot.movie_server.entity.UserEntity;
import com.ayat.springboot.movie_server.dto.MovieCommentary;
import com.ayat.springboot.movie_server.service.MovieCommentaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MovieCommentaryServiceImp implements MovieCommentaryService {

    @Autowired
    private MovieCommentaryRepository movieCommentaryRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public MovieCommentary save(MovieCommentaryEntity movieCommentary, int id, String email) {

        MovieEntity movie = movieRepository.findById(id).get();
        movie.addCommentaryToMovie(movieCommentary);
        UserEntity user = userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User doesn't exists"));
        user.addCommentaryMovieToUser(movieCommentary);
        return MovieCommentary.toMovieCommentary(movieCommentaryRepository.save(movieCommentary));
    }

    @Override
    @Transactional
    public List<MovieCommentary> getAllCommitsWithMovies() {
        return MovieCommentary.toMovieCommentaries(movieCommentaryRepository.findAll());
    }

    @Override
    @Transactional
    public List<MovieCommentary> getCommit(int id) {
        MovieEntity movie = movieRepository.findById(id).get();
        List<MovieCommentaryEntity> commentaryMovie = movie.getMovieCommentaries();
        return MovieCommentary.toMovieCommentaries(commentaryMovie);
    }

    @Override
    public void deleteById(int id) {
//        MovieCommentaryEntity commentary = movieCommentaryRepository.findAllByMovie_TitleEn(titleEnMovie);
        movieCommentaryRepository.deleteById(id);
    }
}
