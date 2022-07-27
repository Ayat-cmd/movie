package com.ayat.springboot.movie_server.dao;

import com.ayat.springboot.movie_server.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    public Optional<UserEntity> findByEmail(String email);

    public Boolean existsByEmail(String email);

    public Boolean existsById(int id);
}
