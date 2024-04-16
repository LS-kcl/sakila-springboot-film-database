package com.example.sakila.dto.in;

import com.example.sakila.entities.Language;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Year;

import static com.example.sakila.dto.in.ValidationGroup.Create;

@Data
public class FilmInput {
    @NotNull(groups = {Create.class})
    @Size(min = 1, max = 255)
    private String title;

    @NotNull(groups = {Create.class})
    @Size(min = 1, max = 2000)
    private String description;

    // Release year must be in the past
    @NotNull(groups = {Create.class})
    @Past
    private Year releaseYear;

    // Rate cannot be below zero
    @NotNull(groups = {Create.class})
    @DecimalMin("0.00")
    private BigDecimal rentalRate;

    @NotNull(groups = {Create.class})
    private Short languageId;

    @NotNull(groups = {Create.class})
    @Column(name="rental_duration")
    private Byte rentalDuration;

    // Must be of positive length or zero
    @NotNull(groups = {Create.class})
    @PositiveOrZero
    private Short length;
}
