package com.example.sakila.controllers;

import com.example.sakila.dto.in.ActorInput;
import com.example.sakila.dto.in.FilmInput;
import com.example.sakila.dto.in.ValidationGroup;
import com.example.sakila.dto.out.ActorOutput;
import com.example.sakila.dto.out.FilmOutput;
import com.example.sakila.entities.Language;
import com.example.sakila.entities.Film;
import com.example.sakila.repositories.FilmRepository;
import com.example.sakila.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    // From: https://www.baeldung.com/rest-api-pagination-in-spring
    @GetMapping(params = { "page", "size" })
    public List<FilmOutput> findPaginated(@RequestParam("page") int page,
                                   @RequestParam("size") int size) {
        // First create a Pageable from request
        Pageable firstPageWithTwoElements = PageRequest.of(page, size);

        // Find all films, and then convert to film output:
        Page<FilmOutput> resultPage = filmRepository.findAll(firstPageWithTwoElements)
                .map(FilmOutput::from);
        // Or alternatively if we wanted to include a query:
        // Page<FilmOutput> resultPage = filmRepository
        //         .findAllByName("Movie_name", firstPageWithTwoElements)
        //         .stream()
        //         .map(FilmOutput::from);

        // List<Product> allTenDollarProducts =
        //         productRepository.findAllByPrice(10, secondPageWithFiveElements);

        return resultPage.getContent();
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FilmOutput update(@Validated(ValidationGroup.Update.class) @RequestBody FilmInput data, @PathVariable Short id){
        var film = filmRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // If a film ID is provided:
        if (data.getLanguageId() != null) {
            Short langId = data.getLanguageId();
            // Check if existing language in repository
            // Return error if non-existent
            Language lang = languageRepository.findById(langId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

            // If language is valid, set
            film.setLanguage(lang);
        }


        if (data.getTitle() != null){
            film.setTitle(data.getTitle());
        }
        if (data.getDescription() != null) {
            film.setDescription(data.getDescription());
        }
        if (data.getReleaseYear() != null) {
            film.setReleaseYear(data.getReleaseYear());
        }
        if (data.getRentalRate() != null) {
            film.setRentalRate(data.getRentalRate());
        }
        if (data.getRentalDuration() != null) {
            film.setRentalDuration(data.getRentalDuration());
        }
        if (data.getLength() != null) {
            film.setLength(data.getLength());
        }

        // Later set the film language to that language

        var saved = filmRepository.save(film);
        return FilmOutput.from(saved);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Short id){
        // Attempt to delete:
        var film = filmRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // Remove foreign key relationship
        // film.setLanguage(null);
        // filmRepository.save(film);
        // Only if found in the repository
        filmRepository.deleteById(id);
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
