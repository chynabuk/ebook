package com.application.electronic_book.model.others;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FavouriteModel {
    private Long userId;
    private List<BookModel> bookModels;
}
