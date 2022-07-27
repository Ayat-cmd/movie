package com.ayat.springboot.movie_server.dao;

import com.ayat.springboot.movie_server.entity.MovieEntity;
import com.ayat.springboot.movie_server.entity.MovieVideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieVideoRepository extends JpaRepository<MovieVideoEntity, Integer> {
}
