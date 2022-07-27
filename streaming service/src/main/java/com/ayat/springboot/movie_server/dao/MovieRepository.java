package com.ayat.springboot.movie_server.dao;

import com.ayat.springboot.movie_server.entity.MovieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {
//    public MovieEntity findByTitleEn(String titleEn);

//    public static List<MovieEntity> Top250Movie() {
//        return null;
//    }
}
