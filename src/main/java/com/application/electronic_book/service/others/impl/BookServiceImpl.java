package com.application.electronic_book.service.others.impl;

import com.application.electronic_book.entity.Book;
import com.application.electronic_book.exception.EBookException;
import com.application.electronic_book.model.others.BookModel;
import com.application.electronic_book.repository.BookRepository;
import com.application.electronic_book.service.others.AuthorService;
import com.application.electronic_book.service.others.BookService;
import com.application.electronic_book.service.others.CategoryService;
import com.application.electronic_book.service.others.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final CategoryService categoryService;
    private final PublisherService publisherService;
    private final AuthorService authorService;

    @Override
    public BookModel create(BookModel bookModel) {
        Book book = Book.builder()
                .name(bookModel.getName())
                .description(bookModel.getDescription())
                .release(bookModel.getRelease())
                .amount(bookModel.getAmount())
                .category(categoryService.getEntityById(bookModel.getCategoryId()))
                .author(authorService.getEntityById(bookModel.getAuthorId()))
                .publisher(publisherService.getEntityById((bookModel.getPublisherId())))
                .img(bookModel.getImg().getBytes(StandardCharsets.UTF_8))
                .build();

        bookRepository.save(book);

        bookModel.setId(book.getId());
        return bookModel;
    }

    @Override
    public String delete(Long id) {
        Book book = getEntityById(id);

        bookRepository.delete(book);

        return "Book with id:" + id + " was deleted";
    }

    @Override
    public Book getEntityById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(()->new EBookException("Book with id:" + id + " was not found"));
    }

    @Override
    public BookModel getById(Long id) {
        return toModel(getEntityById(id));
    }

    @Override
    public List<BookModel> getAll() {
        return null;
    }

    @Override
    public BookModel toModel(Book book) {
        return BookModel.builder()
                .id(book.getId())
                .name(book.getName())
                .description(book.getDescription())
                .release(book.getRelease())
                .authorId(book.getAuthor().getId())
                .categoryId(book.getCategory().getId())
                .publisherId(book.getPublisher().getId())
                .amount(book.getAmount())
                .img(new String(book.getImg(), StandardCharsets.UTF_8))
                .build();
    }

    @Override
    public BookModel update(BookModel bookModel) {
        return null;
    }

    @Override
    public Book toEntity(BookModel bookModel) {
        return Book.builder()
                .name(bookModel.getName())
                .release(bookModel.getRelease())
                .img(bookModel.getImg().getBytes(StandardCharsets.UTF_8))
                .amount(bookModel.getAmount())
                .publisher(publisherService.getEntityById(bookModel.getPublisherId()))
                .author(authorService.getEntityById(bookModel.getAuthorId()))
                .category(categoryService.getEntityById(bookModel.getCategoryId()))
                .description(bookModel.getDescription())
                .build();
    }
}
