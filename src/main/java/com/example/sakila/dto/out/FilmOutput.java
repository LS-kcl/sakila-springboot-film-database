package com.example.sakila.dto.out;
import com.example.sakila.entities.Film;
import com.example.sakila.entities.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class FilmOutput {
    private Short id;
    private String title;
    private String description;
    private BigDecimal rental_rate;
    private Year releaseYear;
    private String language;
    private Byte rentalDuration;
    private Short length;
    private List<ActorReferenceOutput> cast;

    public static FilmOutput from(Film film) {
        return new FilmOutput(
                film.getId(),
                film.getTitle(),
                film.getDescription(),
                film.getRentalRate(),
                film.getReleaseYear(),
                film.getLanguage().getName(),
                film.getRentalDuration(),
                film.getLength(),
                film.getCast().stream().map(ActorReferenceOutput::from).collect(Collectors.toList())
        );
    }
}
