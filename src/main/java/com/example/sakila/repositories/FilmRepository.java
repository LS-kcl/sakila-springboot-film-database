package com.example.sakila.repositories;

import com.example.sakila.entities.Film;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Short> {
    // List<Film> findAllByName(String name, Pageable pageable);
}
