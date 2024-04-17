package com.example.sakila.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    /*
    @GetMapping("/greeting/{id}")
    public String greet(@PathVariable(name="id") Integer id) {
        return "Hello world! ID is: " + id;
    }
     */

    @GetMapping("/greeting")
    public String greet(@RequestParam Integer page) {
        return "Hello world! page is: " + page;
    }
}
