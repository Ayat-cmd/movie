package com.ayat.springboot.movie_server.dao;

import com.ayat.springboot.movie_server.entity.MovieCommentaryEntity;
import com.ayat.springboot.movie_server.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieCommentaryRepository extends JpaRepository<MovieCommentaryEntity, Integer> {
//    public void deleteById(int id);

//    public MovieCommentaryEntity findAllByMovie_TitleEn(String titleEn);//по идее должен находить все комментарии под названием фильма который мы укажем
}
