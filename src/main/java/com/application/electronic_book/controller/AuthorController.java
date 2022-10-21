package com.application.electronic_book.controller;

import com.application.electronic_book.model.others.AuthorModel;
import com.application.electronic_book.model.response.Response;
import com.application.electronic_book.service.others.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping("/create")
    public Response<AuthorModel> create(@RequestBody AuthorModel authorModel){
        return new Response<>(authorService.create(authorModel));
    }

    @GetMapping
    public Response<List<AuthorModel>> getAll(){
        return new Response<>(authorService.getAll());
    }

    @GetMapping("/{id}")
    public Response<AuthorModel> getById(@PathVariable("id") Long id){
        return new Response<>(authorService.getById(id));
    }

}
