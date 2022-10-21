package com.application.electronic_book.service.basic_interfaces;

public interface ToEntityConverter<O, I> {
    O toEntity(I i);
}
