package com.example.sakila.dto.in;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import static com.example.sakila.dto.in.ValidationGroup.Create;

@Data
public class ActorInput {
    @NotNull(groups = {Create.class})
    @Size(min = 1, max = 45)
    private String firstname;

    @NotNull(groups = {Create.class})
    @Size(min = 1, max = 45)
    private String lastname;
}
