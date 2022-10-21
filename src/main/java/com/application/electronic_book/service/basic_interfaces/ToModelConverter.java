package com.application.electronic_book.service.basic_interfaces;

public interface ToModelConverter<O, I> {
    O toModel(I i);
}