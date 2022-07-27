package com.ayat.springboot.movie_server.service.impl;

import com.ayat.springboot.movie_server.dao.UserRepository;
import com.ayat.springboot.movie_server.entity.UserEntity;
import com.ayat.springboot.movie_server.dto.User;
import com.ayat.springboot.movie_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User getUserEmail(String email) {
        return User.toUser(userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User doesn't exists")));
    }

    @Override
    public List<User> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return User.toUsers(users);
    }

    @Override
    public User saveOrUpdateUser(UserEntity user) {
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        return User.toUser(userRepository.save(user));
    }

    @Override
    public User updateUser(UserEntity entity) {
//        UserEntity
        return null;
    }

    @Override
    public User getUser(int id) {
        UserEntity userEntity = userRepository.findById(id).get();
        return User.toUser(userEntity);
    }

    @Override
    public void removeUser(String email) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User doesn't exists"));
        userRepository.delete(user);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Boolean existsById(int id) {
        return userRepository.existsById(id);
    }
}
