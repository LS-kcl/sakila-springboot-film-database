package com.example.sakila.repositories;

import com.example.sakila.entities.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.GetMapping;

public interface ActorRepository extends CrudRepository<Actor, Short> {
}
