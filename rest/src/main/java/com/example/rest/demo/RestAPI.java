package com.example.rest.demo;

import org.springframework.web.bind.annotation.*;

@RestController
public class RestAPI {

    @GetMapping("/books")
    public String GetAll() {
        return "getall";
    }

    @PostMapping("/add")
    public String Add(@RequestBody Book book) {
        return "add";
    }

    @PutMapping("/update/{id}")
    public String Update(@RequestBody Book book, @PathVariable int id) {
        return "update";
    }

    @DeleteMapping("/delete/{id}")
    public String del(@PathVariable int id
    ) {
        return "delete";
    }
}
