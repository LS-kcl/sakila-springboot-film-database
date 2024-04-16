package com.example.sakila.dto.out;

import com.example.sakila.entities.Actor;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ActorOutput {
    private Short id;
    private String firstName;
    private String lastName;

    public static ActorOutput from(Actor actor) {
        return new ActorOutput(actor.getId(), actor.getFirstname(), actor.getLastname());
    }
}
