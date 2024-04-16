package com.example.sakila.repositories;

import com.example.sakila.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Short> {
}
