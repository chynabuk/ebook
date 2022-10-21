package com.application.electronic_book.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationModel extends UserAuthModel{
    private String fullName;
    private String phone;
    private Long groupId;
}