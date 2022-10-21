package com.application.electronic_book.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserTokenModel {
    private String email;
    private String accessToken;
}