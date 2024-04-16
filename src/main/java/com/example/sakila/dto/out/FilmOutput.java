package com.example.sakila.dto.out;
import com.example.sakila.entities.Film;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FilmOutput {
    private Short id;
    private String title;
    private String description;

    public static FilmOutput from(Film film) {
        return new FilmOutput(film.getId(), film.getTitle(), film.getDescription());
    }
}
