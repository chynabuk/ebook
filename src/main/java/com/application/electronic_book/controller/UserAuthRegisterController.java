package com.application.electronic_book.controller;

import com.application.electronic_book.model.response.Response;
import com.application.electronic_book.model.user.UserAuthModel;
import com.application.electronic_book.model.user.UserModel;
import com.application.electronic_book.model.user.UserRegistrationModel;
import com.application.electronic_book.model.user.UserTokenModel;
import com.application.electronic_book.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserAuthRegisterController {
    private final UserService userService;
//
//    @PostMapping("/login")
//    public Response<UserTokenModel> login(@RequestBody UserAuthModel userAuthModel){
//        return new Response<>(userService.login(userAuthModel));
//    }

    @PostMapping("/register")
    public Response<UserModel> login(@RequestBody UserRegistrationModel userRegistrationModel){
        return new Response<>(userService.create(userRegistrationModel));
    }
}
