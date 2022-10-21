package com.application.electronic_book.controller;

import com.application.electronic_book.model.others.PublisherModel;
import com.application.electronic_book.model.response.Response;
import com.application.electronic_book.service.others.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/publisher")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    @PostMapping("/create")
    public Response<PublisherModel> create(@RequestBody PublisherModel publisherModel){
        return new Response<>(publisherService.create(publisherModel));
    }

    @GetMapping
    public Response<List<PublisherModel>> getAll(){
        return new Response<>(publisherService.getAll());
    }

    @GetMapping("/{id}")
    public Response<PublisherModel> getById(@PathVariable("id") Long id){
        return new Response<>(publisherService.getById(id));
    }



}
