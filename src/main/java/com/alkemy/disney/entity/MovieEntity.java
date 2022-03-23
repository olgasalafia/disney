package com.alkemy.disney.entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movie")
@Getter
@Setter
@SQLDelete(sql = "UPDATE movie SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String image;
    private String title;
    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate creationDate;
    private Integer score;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "genre_id", insertable = false, updatable = false)
    private GenreEntity genre;
    @Column(name = "genre_id", nullable = false)
    private Long genreId;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "personage_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "personage_id"))
    private Set<PersonageEntity> personages = new HashSet<>();
    private Boolean deleted = Boolean.FALSE;
    public void addPersonage(PersonageEntity personage) {this.personages.add(personage);}
    public void removePersonage(PersonageEntity personage) {this.personages.remove(personage);}
}
