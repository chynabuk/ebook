package com.application.electronic_book.controller;

import com.application.electronic_book.model.others.CategoryModel;
import com.application.electronic_book.model.response.Response;
import com.application.electronic_book.service.others.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create")
    public Response<CategoryModel> create(@RequestBody CategoryModel categoryModel){
        return new Response<>(categoryService.create(categoryModel));
    }

    @GetMapping
    public Response<List<CategoryModel>> getAll(){
        return new Response<>(categoryService.getAll());
    }

    @GetMapping("/{id}")
    public Response<CategoryModel> getById(@PathVariable("id") Long id){
        return new Response<>(categoryService.getById(id));
    }

}
