package com.example.sakila.dto.in;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ActorInput {
    @NotNull
    @Size(min = 1, max = 45)
    private String firstname;

    @NotNull
    @Size(min = 1, max = 45)
    private String lastname;
}
