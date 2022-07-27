package com.ayat.springboot.movie_server.controller;

import com.ayat.springboot.movie_server.dto.Movie;
import com.ayat.springboot.movie_server.entity.MovieEntity;
import com.ayat.springboot.movie_server.entity.MovieVideoEntity;
import com.ayat.springboot.movie_server.exception_handler.MessageResponse;
import com.ayat.springboot.movie_server.exception_handler.MovieIncorrectIdException;
import com.ayat.springboot.movie_server.service.MovieService;
import com.ayat.springboot.movie_server.service.MovieVideoService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {

    @Autowired
    private MovieService moviesService;

    @Autowired
    private MovieVideoService movieVideoService;

    @GetMapping("/movies")
//    @PreAuthorize("hasAuthority('developers:read')")
    public ResponseEntity getAllMovies(){

        return ResponseEntity.ok(moviesService.getAllMovies());
    }

    @SneakyThrows
    @RequestMapping("/saveVideo")
    //    @PreAuthorize("hasAuthority('developers:write')")
    public String saveVideo() {
        List<Movie> movies = moviesService.getAllMovies();
        movieVideoService.findVideoToMovie(movies);
        return "done";
    }

    @SneakyThrows
    @RequestMapping("/save")
//    @PreAuthorize("hasAuthority('developers:write')")
    public String getMovieApiImdb() {// TODO исправь сделай запись фильмов в БД по 200 штук
        ObjectMapper mapper = new ObjectMapper(); // create once, reuse
        int page = 1;
        List<MovieEntity> movies;
        do{
            URL url = new URL("https://kinoplayer.top/index.php?do=api&token=9b19e074e3c819fcbeb16c775484a8d6&list&page="+page);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((con.getInputStream())));
            JsonNode root = mapper.readTree(bufferedReader);
            JsonNode moviesJson = root.get("results");


            if (moviesJson.isEmpty()){
                break;
            }
            movies = new ArrayList<>(moviesJson.size());
            for (int i = 0; i < moviesJson.size(); i++){
                String movie = String.valueOf(moviesJson.get(String.valueOf(i)));
                JsonNode imdbtmp = moviesJson.get(String.valueOf(i));
                JsonNode imdb = imdbtmp.get("imdb_id");
                String imdbId = String.valueOf(imdb);
                imdbId = imdbId.replace("\"", "");
                if (!imdbId.equals("null")) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    final String myKey = "k_fiu7uqoi";
                    URL urll = new URL("https://imdb-api.com/en/API/Ratings/" + myKey + "/" + imdbId);
                    HttpURLConnection conn = (HttpURLConnection) urll.openConnection();

                    conn.setDoOutput(true);
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Content-Type", "application/json");

                    BufferedReader rdr = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                    JsonNode obj = objectMapper.readTree(rdr);
                    JsonNode moviesRatingJson = obj.get("imDb");
                    String movieRating = String.valueOf(moviesRatingJson);
                    if (movieRating.isEmpty() || movieRating.equals("null"))
                        movieRating = "0";
                    movie = movie.replace("}", "");
                    movie += ",\"ratings\":" + movieRating + "}";
                } else {
                    movie = movie.replace("}", "");
                    movie += ",\"ratings\":\"0\"}";
                }
                movies.add(mapper.readValue(movie, MovieEntity.class));
            }
            moviesService.saveAll(movies);
            page++;
        }while (true);
        return "Done";
    }

    @GetMapping("/movies/{id}")
//    @PreAuthorize("hasAuthority('developers:read')")
    public ResponseEntity<?> getMovie(@PathVariable int id) {
        try {
            return  ResponseEntity.ok(moviesService.getMovie(id));
        } catch (Exception e) {
            throw new MovieIncorrectIdException("Фильма с таким id нету");
        }
    }

    @DeleteMapping("/movies")
    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<?> removeMovie(@RequestParam(name = "id") int id) {
        try {
            moviesService.deleteById(id);
            return ResponseEntity.ok(new MessageResponse("Фильм удален"));
        } catch (RuntimeException e) {
            throw new MovieIncorrectIdException("Удалять нечего, либо указан неверный id");
        }
    }
}
