package com.example.sakila.controllers;

import com.example.sakila.entities.Actor;
import com.example.sakila.repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActorController{

    @Autowired
    private ActorRepository actorRepository;

    @GetMapping("/actors")
    public Iterable<Actor> readAll() {
        return actorRepository.findAll();
    }
}
