package com.ayat.springboot.movie_server.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "video")
@RequiredArgsConstructor
public class MovieVideoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NonNull
    @Column(name = "video_id")
    private String videoId;

    @OneToOne
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;

    @Override
    public String toString() {
        return "MovieVideoEntity{" +
                "id=" + id +
                ", videoId=" + videoId +
                '}';
    }
}
