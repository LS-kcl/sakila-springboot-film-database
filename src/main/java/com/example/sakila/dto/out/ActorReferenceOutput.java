package com.example.sakila.dto.out;

import com.example.sakila.entities.Actor;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ActorReferenceOutput {
    private Short id;
    private String fullname;

    public static ActorReferenceOutput from(Actor actor) {
        return new ActorReferenceOutput(
                actor.getId(),
                actor.getFirstname() + " " + actor.getLastname()
        );
    }
}
