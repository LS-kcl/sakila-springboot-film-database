package com.example.sakila.dto.out;
import com.example.sakila.entities.Film;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class FilmOutput {
    private Short id;
    private String title;
    private String description;
    private BigDecimal rental_rate;

    public static FilmOutput from(Film film) {
        return new FilmOutput(film.getId(), film.getTitle(), film.getDescription(), film.getRental_rate());
    }
}
