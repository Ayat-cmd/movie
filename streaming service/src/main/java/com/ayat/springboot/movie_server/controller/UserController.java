package com.ayat.springboot.movie_server.controller;

import com.ayat.springboot.movie_server.exception_handler.MessageResponse;
import com.ayat.springboot.movie_server.entity.UserEntity;
import com.ayat.springboot.movie_server.exception_handler.NoSuchUserException;
import com.ayat.springboot.movie_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
//    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<?> getAllUsers() {
        //TODO удали из БД всех пользователей и проверь что вернет
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/users")
//    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<?> addUser(@RequestBody UserEntity user) {
        if (userService.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is exists"));
        }
        return ResponseEntity.ok(userService.saveOrUpdateUser(user));
    }

    @GetMapping("/users/{id}")
//    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<?> getUser(@PathVariable(name = "id") int id) {
        try {
            return ResponseEntity.ok(userService.getUser(id));
        } catch (RuntimeException e) {
            throw new NoSuchUserException("Пользователя с id = " + id + " нет в БД");
        }
    }

    @DeleteMapping("/users")
//    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<?> removeUser(@RequestParam(name="email") String email) {
        try {
            userService.removeUser(email);
            return ResponseEntity.ok(new MessageResponse("User "+email+" delete"));
        } catch (RuntimeException e) {
            throw new NoSuchUserException("Пользователя с email= " + email + "  нету в БД");
        }
    }

    @PutMapping("/users")
//    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<?> updateUser(@RequestBody UserEntity userUpdate) {
        try {
            return ResponseEntity.ok(userService.saveOrUpdateUser(userUpdate));
        } catch (RuntimeException e) {
            throw new NoSuchUserException("Пользователя с id = " + userUpdate.getId() + " нет в БД");
        }
    }
}
