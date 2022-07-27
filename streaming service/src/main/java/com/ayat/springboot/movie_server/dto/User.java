package com.ayat.springboot.movie_server.dto;

import com.ayat.springboot.movie_server.entity.Role;
import com.ayat.springboot.movie_server.entity.Status;
import com.ayat.springboot.movie_server.entity.UserEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    private int id;
    private String email;
    private String username;
    private String surname;
    private Role role;
    private Status status;

    public static User toUser(UserEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setEmail(entity.getEmail());
        user.setUsername(entity.getUsername());
        user.setSurname(entity.getSurname());
        user.setRole(entity.getRole());
        user.setStatus(entity.getStatus());
        return user;
    }

    public static List<User> toUsers(List<UserEntity> entities) {
        List<User> users = new ArrayList<>();
        for (UserEntity entity : entities) {
            users.add(toUser(entity));
        }
        return users;
    }
}
