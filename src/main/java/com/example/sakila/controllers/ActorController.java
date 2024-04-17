package com.example.sakila.controllers;

import com.example.sakila.dto.in.ActorInput;
import com.example.sakila.dto.in.ValidationGroup;
import com.example.sakila.dto.out.ActorOutput;
import com.example.sakila.entities.Actor;
import com.example.sakila.repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static com.example.sakila.dto.in.ValidationGroup.Create;
import static com.example.sakila.dto.in.ValidationGroup.Update;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/actors")
public class ActorController{

    @Autowired
    private ActorRepository actorRepository;

    @GetMapping
    public List<EntityModel<ActorOutput>> readAll() {
        final var actors = actorRepository.findAll();
        return actors.stream()
                .map(ActorOutput::from)
                .map(actor -> EntityModel.of(actor,
                        linkTo(methodOn(ActorController.class).readById(actor.getId())).withSelfRel(),
                        linkTo(methodOn(ActorController.class).readAll()).withRel("actors")))
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<ActorOutput> create(@Validated(Create.class) @RequestBody ActorInput data){
        final var actor = new Actor();
        actor.setFirstname(data.getFirstname());
        actor.setLastname(data.getLastname());
        var saved = actorRepository.save(actor);
        var actorOutput = ActorOutput.from(saved);
        // Get the id of the newly created object to redirect to:
        Short actorId = actorOutput.getId();
        return EntityModel.of(actorOutput, //
                linkTo(methodOn(ActorController.class).readById(actorId)).withSelfRel(),
                linkTo(methodOn(ActorController.class).readAll()).withRel("actors"));
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

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<ActorOutput> update(@Validated(Update.class) @RequestBody ActorInput data, @PathVariable Short id){
        var actor = actorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // If firstname provided
        if (data.getFirstname()!=null){
            actor.setFirstname(data.getFirstname());
        }

        // If lastname provided
        if (data.getLastname()!=null){
            actor.setLastname(data.getLastname());
        }

        var saved = actorRepository.save(actor);
        var actorOutput = ActorOutput.from(saved);
        return EntityModel.of(actorOutput, //
                linkTo(methodOn(ActorController.class).readById(id)).withSelfRel(),
                linkTo(methodOn(ActorController.class).readAll()).withRel("actors"));
    }

    @GetMapping("/{id}")
    public EntityModel<ActorOutput> readById(@PathVariable Short id) {
        // Get the actor output
        var actorOutput = actorRepository.findById(id)
                .map(ActorOutput::from)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("No such found actor of id %d", id)
                ));

        // Return actor output wrapped in entity model
        // Adapted from: https://spring.io/guides/tutorials/rest
        return EntityModel.of(actorOutput, //
                linkTo(methodOn(ActorController.class).readById(id)).withSelfRel(),
                linkTo(methodOn(ActorController.class).readAll()).withRel("actors"));
    }
}
