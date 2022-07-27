package com.ayat.springboot.movie_server.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Setter @Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name="movie_commentarys")
public class MovieCommentaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "commentary")
    @NonNull
    private String commentary;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    @NonNull
    private MovieEntity movie;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @NonNull
    private UserEntity user;

//    public MovieCommentaryEntity(String commentary, MovieEntity movies) {
//        this.commentary = commentary;
//        this.movie = movies;
//    }

}
