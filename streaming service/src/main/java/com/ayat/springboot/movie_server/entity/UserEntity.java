package com.ayat.springboot.movie_server.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Setter @Getter
@Table(name = "users")
@RequiredArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NonNull
    @Column(name = "email")
    private String email;

    @NonNull
    @Column(name = "username")
    private String username;

    @NonNull
    @Column(name = "surname")
    private String surname;

    @NonNull
    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private Status status;

    public UserEntity() {
        this.role = Role.valueOf("USER");
        this.status = Status.valueOf("ACTIVE");
    }

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "user")
    private Set<MovieCommentaryEntity> movieCommentaries;

    public void addCommentaryMovieToUser(MovieCommentaryEntity movieCommentary) {
        movieCommentary.setUser(this);
    }
}