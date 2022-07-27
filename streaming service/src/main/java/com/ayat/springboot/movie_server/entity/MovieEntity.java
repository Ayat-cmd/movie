package com.ayat.springboot.movie_server.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter @Getter
@NoArgsConstructor
@Table(name = "movies")
@RequiredArgsConstructor
@JsonIgnoreProperties({ "date", "year", "directors", "writers",
        "composers", "editors", "designers", "operators", "age_restrictions"})
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NonNull
    @Column(name = "kp_id")
    private int kp_id;

    @NonNull
    @Column(name = "imdb_id")
    private String imdb_id;

    @NonNull
    @Column(name = "title_ru")
    private String title_ru;

    @NonNull
    @Column(name = "title_en")
    private String title_en;

    @NonNull
    @Column(name = "poster")
    private String poster;

    @NonNull
    @Column(name = "country")
    private String country;

    @NonNull
    @Column(name = "genre")
    private String genre;

    @NonNull
    @Column(name = "actors")
    private String actors;

    @NonNull
    @Column(name = "producers")
    private String producers;

    @NonNull
    @Column(name = "premiere")
    private String premiere;

    @NonNull
    @Column(name = "descrip")
    private String description;

    @NonNull
    @Column(name = "ratings")
    private String ratings;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie")
    List<MovieCommentaryEntity> movieCommentaries;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "movie")
    MovieVideoEntity video;

    public void addCommentaryToMovie(MovieCommentaryEntity movieCommentary) {
        movieCommentary.setMovie(this);
    }

    public void addVideoToMovie(MovieVideoEntity movieVideoEntity) {
        movieVideoEntity.setMovie(this);
    }

    @Override
    public String toString() {
        return "MovieEntity{" +
                "id=" + id +
                ", kp_id=" + kp_id +
                ", imdb_id='" + imdb_id + '\'' +
                ", title_ru='" + title_ru + '\'' +
                ", title_en='" + title_en + '\'' +
                ", poster='" + poster + '\'' +
                ", country='" + country + '\'' +
                ", genre='" + genre + '\'' +
                ", actors='" + actors + '\'' +
                ", producers='" + producers + '\'' +
                ", premiere='" + premiere + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
//Todo добавь год year нужен для фронта