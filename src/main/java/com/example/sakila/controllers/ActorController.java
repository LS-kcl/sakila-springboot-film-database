package com.example.sakila.controllers;

import com.example.sakila.dto.in.ActorInput;
import com.example.sakila.dto.out.ActorOutput;
import com.example.sakila.entities.Actor;
import com.example.sakila.repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import static com.example.sakila.dto.in.ValidationGroup.Create;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/actors")
public class ActorController{

    @Autowired
    private ActorRepository actorRepository;

    @GetMapping
    public List<ActorOutput> readAll() {
        final var actors = actorRepository.findAll();
        return actors.stream()
                .map(ActorOutput::from)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ActorOutput create(@Validated(Create.class) @RequestBody ActorInput data){
        final var actor = new Actor();
        actor.setFirstname(data.getFirstname());
        actor.setLastname(data.getLastname());
        var saved = actorRepository.save(actor);
        return ActorOutput.from(saved);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Short id){
        // Attempt to delete:
        actorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // Only if found in repository
        actorRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public ActorOutput readById(@PathVariable Short id) {
        return actorRepository.findById(id)
                .map(ActorOutput::from)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("No such found actor of id %d", id)
                ));
    }
}
