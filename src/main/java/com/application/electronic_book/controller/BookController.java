package com.application.electronic_book.controller;

import com.application.electronic_book.entity.Book;
import com.application.electronic_book.model.others.BookModel;
import com.application.electronic_book.model.response.Response;
import com.application.electronic_book.service.others.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping("/admin/create")
    public Response<BookModel> create(@RequestBody BookModel bookModel){
        return new Response<>(bookService.create(bookModel));
    }

    @GetMapping("/get/{id}")
    public Response<BookModel> getById(@PathVariable Long id){
        return new Response<>(bookService.getById(id));
    }

    @GetMapping("/get-all")
    public Response<List<BookModel>> getAll(
            @RequestParam(required = false) String sorting,
            @RequestParam(required = false, defaultValue = "0", name = "filter_by_category") Long filterByCategory,
            @RequestParam(required = false, defaultValue = "0", name = "filter_by_author") Long filterByAuthor,
            @RequestParam(required = false) String search
            ){
        return new Response<>(bookService.getAll(sorting, filterByCategory, filterByCategory, search));
    }
}
