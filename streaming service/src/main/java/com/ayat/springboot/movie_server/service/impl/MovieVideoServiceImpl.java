package com.ayat.springboot.movie_server.service.impl;

import com.ayat.springboot.movie_server.dao.MovieRepository;
import com.ayat.springboot.movie_server.dao.MovieVideoRepository;
import com.ayat.springboot.movie_server.dto.Movie;
import com.ayat.springboot.movie_server.entity.MovieEntity;
import com.ayat.springboot.movie_server.entity.MovieVideoEntity;
import com.ayat.springboot.movie_server.service.MovieVideoService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class MovieVideoServiceImpl implements MovieVideoService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieVideoRepository movieVideoRepository;

    @SneakyThrows
    @Override
    public void findVideoToMovie(List<Movie> movies) {
        for (int i = 0; i < movies.size(); i++) {
            String imdbId = movies.get(i).getImdbId();
            ObjectMapper mapperVideoId = new ObjectMapper();
            URL urlVideoId = new URL("https://imdb-api.com/en/API/Trailer/k_ybt2sr4q/"+imdbId);
            HttpURLConnection conVideoId = (HttpURLConnection) urlVideoId.openConnection();
            conVideoId.setDoOutput(true);
            conVideoId.setRequestMethod("GET");
            conVideoId.setRequestProperty("Content-Type", "application/json; utf-8");
            BufferedReader bufferedReaderVideoId = new BufferedReader(new InputStreamReader((conVideoId.getInputStream())));
            JsonNode rootVideoId = mapperVideoId.readTree(bufferedReaderVideoId);
            JsonNode videoIdJson = rootVideoId.get("videoId");
            String videoId = videoIdJson.toString();
            videoId = videoId.replace("\"", "");
            saveVideo(videoId, movies.get(i).getId());
        }
    }

    @Override
    public void saveVideo(String video, int idMovie) {
        MovieEntity movie = movieRepository.findById(idMovie).get();
        MovieVideoEntity videoEntity = new MovieVideoEntity();
        videoEntity.setVideoId(video);
        movie.addVideoToMovie(videoEntity);

        movieVideoRepository.save(videoEntity);
    }
}
