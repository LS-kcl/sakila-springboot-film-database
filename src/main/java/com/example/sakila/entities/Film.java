package com.example.sakila.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Year;

@Getter
@Entity
@Table(name="film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="film_id")
    private Short id;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="release_year")
    private Year releaseYear;

    @Column(name="rental_rate")
    private BigDecimal rentalRate;

    @ManyToOne
    @JoinColumn(name="language_id")
    private Language language;

    @Column(name="rental_duration")
    private Byte rentalDuration;

    @Column(name="length")
    private Short length;

}
