package com.example.sakila.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name="actor")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="actor_id")
    private Short id;

    @Column(name="first_name")
    private String firstname;

    @Column(name="last_name")
    private String lastname;
}
