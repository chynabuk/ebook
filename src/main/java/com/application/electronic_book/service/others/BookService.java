package com.application.electronic_book.service.others;

import com.application.electronic_book.entity.Book;
import com.application.electronic_book.model.others.BookModel;
import com.application.electronic_book.service.basic_interfaces.*;

import java.util.List;

public interface BookService extends
        Create<BookModel, BookModel>,
        Read<BookModel, Book>,
        Delete,
        Update<BookModel>,
        ToModelConverter<BookModel, Book>,
        ToEntityConverter<Book, BookModel>
{
    List<BookModel> getAll(String sorting, Long filterByCategory, Long filterByAuthor, String search);
}
