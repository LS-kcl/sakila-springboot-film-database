package com.example.sakila.controllers;

import com.example.sakila.dto.out.ActorOutput;
import com.example.sakila.dto.out.FilmOutput;
import com.example.sakila.entities.Film;
import com.example.sakila.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/films")
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;

    @GetMapping
    public List<FilmOutput> readAll() {
        final var films = filmRepository.findAll();
        return films.stream()
                .map(FilmOutput::from)
                .collect(Collectors.toList());
    }

    // public Iterable<Film> readAll() {
    //     return filmRepository.findAll();
    // }

    // @GetMapping("/{id}")
    // public Iterable<Film> readByID() {
    //     return filmRepository.findAll();
    // }
}
