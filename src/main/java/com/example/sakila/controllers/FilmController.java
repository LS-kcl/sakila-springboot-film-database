package com.example.sakila.controllers;

import com.example.sakila.dto.in.FilmInput;
import com.example.sakila.dto.in.ValidationGroup;
import com.example.sakila.dto.out.ActorOutput;
import com.example.sakila.dto.out.FilmOutput;
import com.example.sakila.entities.Language;
import com.example.sakila.entities.Film;
import com.example.sakila.repositories.FilmRepository;
import com.example.sakila.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static com.example.sakila.dto.in.ValidationGroup.Create;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/films")
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @GetMapping
    public List<FilmOutput> readAll() {
        final var films = filmRepository.findAll();
        return films.stream()
                .map(FilmOutput::from)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public FilmOutput readByID(@PathVariable Short id) {
        return filmRepository.findById(id)
                .map(FilmOutput::from)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("No such found film of id %d", id)
                ));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FilmOutput create(@Validated(Create.class) @RequestBody FilmInput data){
        final var film = new Film();

        System.out.println(data);

        // Take language id from data
        Short langId = data.getLanguageId();

        // Check if existing language in repository
        // Return error if non-existent
        Language lang = languageRepository.findById(langId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        // Else return that language object

        film.setTitle(data.getTitle());
        film.setDescription(data.getDescription());
        film.setReleaseYear(data.getReleaseYear());
        film.setRentalRate(data.getRentalRate());
        // Later set the film language to that language
        film.setLanguage(lang);
        film.setRentalDuration(data.getRentalDuration());
        film.setLength(data.getLength());

        var saved = filmRepository.save(film);
        return FilmOutput.from(saved);
    }
}
