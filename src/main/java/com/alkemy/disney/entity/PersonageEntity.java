package com.alkemy.disney.entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "personage")
@Getter
@Setter
@SQLDelete(sql = "UPDATE personage SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class PersonageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String image;
    private String name;
    private Integer age;
    private Double weight;
    private String history;
    @ManyToMany(mappedBy = "personages", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<MovieEntity> movies = new ArrayList<>();
    private Boolean deleted = Boolean.FALSE;
    public void addMovie(MovieEntity movie) {this.movies.add(movie);}
    public void removeMovie(MovieEntity movie) {this.movies.remove(movie);}
}
