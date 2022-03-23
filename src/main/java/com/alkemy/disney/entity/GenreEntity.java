package com.alkemy.disney.entity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;


import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "genre")
@Getter
@Setter
@SQLDelete(sql = "UPDATE genre SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String image;
    private Boolean deleted = Boolean.FALSE;
}
