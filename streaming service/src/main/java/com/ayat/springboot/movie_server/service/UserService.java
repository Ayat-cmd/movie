package com.ayat.springboot.movie_server.service;

import com.ayat.springboot.movie_server.entity.UserEntity;
import com.ayat.springboot.movie_server.dto.User;

import java.util.List;

public interface UserService {
    public User getUserEmail(String email);

    public List<User> getAllUsers();

    public User saveOrUpdateUser(UserEntity user);

    public User updateUser(UserEntity user);

    public User getUser(int id);

    public void removeUser(String email);

    public Boolean existsByEmail(String email);

    public Boolean existsById(int id);
}
