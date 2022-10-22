package com.application.electronic_book.model.others;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class OrderModel {
    private Long id;
    private Long userId;
    private Long bookId;
    private Integer dayAmount;
    private String token;
    private LocalDateTime tokenExpirationDate;
}