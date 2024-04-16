package com.example.sakila.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;

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

    @Column(name="rental_rate")
    private BigDecimal rental_rate;
}
