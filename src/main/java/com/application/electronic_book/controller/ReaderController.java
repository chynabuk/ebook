package com.application.electronic_book.controller;

import com.application.electronic_book.model.others.FavouriteModel;
import com.application.electronic_book.model.others.OrderModel;
import com.application.electronic_book.model.response.Response;
import com.application.electronic_book.service.others.FavouriteService;
import com.application.electronic_book.service.others.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reader")
@RequiredArgsConstructor
public class ReaderController {
    private final FavouriteService favouriteService;
    private final OrderService orderService;

    @PostMapping("/add-to-favourites")
    public Response<FavouriteModel> addToFavourites(@RequestBody FavouriteModel favouriteModel){
        return new Response<>(favouriteService.create(favouriteModel));
    }

    @GetMapping("/get/{id}/favourite-books")
    public Response<FavouriteModel> getFavourites(@PathVariable Long id){
        return new Response<>(favouriteService.getById(id));
    }

    @PostMapping("/order")
    public Response<OrderModel> order(@RequestBody OrderModel orderModel){
        return new Response<>(orderService.create(orderModel));
    }
}
