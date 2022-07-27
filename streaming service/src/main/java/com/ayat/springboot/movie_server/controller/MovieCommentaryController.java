package com.ayat.springboot.movie_server.controller;

import com.ayat.springboot.movie_server.entity.MovieCommentaryEntity;
import com.ayat.springboot.movie_server.exception_handler.MessageResponse;
import com.ayat.springboot.movie_server.exception_handler.MovieIncorrectIdException;
import com.ayat.springboot.movie_server.exception_handler.NoSuchComment;
import com.ayat.springboot.movie_server.service.MovieCommentaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MovieCommentaryController {

    @Autowired
    private MovieCommentaryService movieCommentaryService;

    @PostMapping("/commentaries")
    @PreAuthorize("hasAuthority('developers:read')")
    public ResponseEntity<?> addCommentToMovie(@RequestBody MovieCommentaryEntity movieCommentary, @RequestParam int id,
                                            @RequestParam String email) {
        try{
            return ResponseEntity.ok(movieCommentaryService.save(movieCommentary, id, email));
        } catch (RuntimeException e) {
            throw new MovieIncorrectIdException("Нету пользователя или фильма в БД!");
        }
    }

    @DeleteMapping("/commentaries")
//    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<?> removeCommit(@RequestParam(name = "id") int idCommit) {
        try {
            movieCommentaryService.deleteById(idCommit);
            return ResponseEntity.ok(new MessageResponse("комментарий к фильму удален"));
        } catch (RuntimeException e) {
            throw new MovieIncorrectIdException("Такого коментария нету");
        }
    }

    @GetMapping("/commentaries")
//    @PreAuthorize("hasAuthority('developers:read')")
    public ResponseEntity<?> getAllCommit() {
        return ResponseEntity.ok(movieCommentaryService.getAllCommitsWithMovies());
    }

    @GetMapping("/commentaries/{id}")
//    @PreAuthorize("hasAuthority('developers:read')")
    public ResponseEntity<?> getCommit(@PathVariable int id) {
        try{
            return ResponseEntity.ok(movieCommentaryService.getCommit(id));
        } catch (RuntimeException e) {
            throw new NoSuchComment("комментария с таким id нет в БД");
        }
    }
}
