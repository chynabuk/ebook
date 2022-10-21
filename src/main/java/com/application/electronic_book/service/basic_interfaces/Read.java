package com.application.electronic_book.service.basic_interfaces;

import java.util.List;

public interface Read<T, D>{
    D getEntityById(Long id);
    T getById(Long id);

    List<T> getAll();
}
