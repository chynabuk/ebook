package com.application.electronic_book.controller;

import com.application.electronic_book.model.others.OrderModel;
import com.application.electronic_book.model.response.Response;
import com.application.electronic_book.service.others.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/order/{token}")
    public Response<OrderModel> getByToken(@PathVariable String token){
        return new Response<>(orderService.toModel(orderService.getByToken(token)));
    }

    @GetMapping("/order/get-all")
    public Response<List<OrderModel>> getAll(){
        return new Response<>(orderService.getAll());
    }

    @PostMapping("/order/{token}")
    public Response<OrderModel> confirm(@PathVariable String token, @RequestParam(name = "day_amount") Integer dayAmount){
        return new Response<>(orderService.confirmOrder(token, dayAmount));
    }


}
