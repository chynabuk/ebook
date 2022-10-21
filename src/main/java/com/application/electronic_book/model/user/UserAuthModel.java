package com.application.electronic_book.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthModel {
    private Long id;
    private String email;
    private String password;
}
