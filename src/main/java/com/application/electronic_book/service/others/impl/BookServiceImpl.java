package com.application.electronic_book.service.others.impl;

import com.application.electronic_book.entity.Author;
import com.application.electronic_book.entity.Book;
import com.application.electronic_book.entity.Category;
import com.application.electronic_book.entity.Publisher;
import com.application.electronic_book.exception.EBookException;
import com.application.electronic_book.model.others.BookModel;
import com.application.electronic_book.repository.AuthorRepository;
import com.application.electronic_book.repository.BookRepository;
import com.application.electronic_book.repository.CategoryRepository;
import com.application.electronic_book.repository.PublisherRepository;
import com.application.electronic_book.service.others.AuthorService;
import com.application.electronic_book.service.others.BookService;
import com.application.electronic_book.service.others.CategoryService;
import com.application.electronic_book.service.others.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    @Override
    public BookModel create(BookModel bookModel) {
        Category category = categoryRepository.findCategoryByName(bookModel.getCategoryName()).orElse(null);
        Publisher publisher = publisherRepository.findPublisherByName(bookModel.getPublisherName()).orElse(null);
        Author author = authorRepository.findAuthorByFullName(bookModel.getAuthorName()).orElse(null);

        if (category == null){
            category = new Category();
            category.setName(bookModel.getCategoryName());
            categoryRepository.save(category);
        }
        if (publisher == null){
            publisher = new Publisher();
            publisher.setName(bookModel.getPublisherName());
            publisherRepository.save(publisher);
        }
        if (author == null){
            author = new Author();
            author.setFullName(bookModel.getAuthorName());
            authorRepository.save(author);
        }

        Book book = Book.builder()
                .name(bookModel.getName())
                .description(bookModel.getDescription())
                .release(bookModel.getRelease())
                .amount(bookModel.getAmount())
                .category(category)
                .author(author)
                .publisher(publisher)
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
        return bookRepository.findAll()
                .stream()
                .map(book -> toModel(book))
                .collect(Collectors.toList());
    }

    @Override
    public BookModel toModel(Book book) {
        return BookModel.builder()
                .id(book.getId())
                .name(book.getName())
                .description(book.getDescription())
                .release(book.getRelease())
                .authorName(book.getAuthor().getFullName())
                .categoryName(book.getCategory().getName())
                .publisherName(book.getPublisher().getName())
                .amount(book.getAmount())
                .img(new String(book.getImg(), StandardCharsets.UTF_8))
                .build();
    }

    @Override
    public BookModel update(BookModel bookModel) {
        Book book = getEntityById(bookModel.getId());
        if(book==null){
            throw new EBookException("Book with ID " +bookModel.getId() + " was not found!");
        }
        bookRepository.save(book);
        return bookModel;
    }

    @Override
    public Book toEntity(BookModel bookModel) {
        return Book.builder()
                .name(bookModel.getName())
                .release(bookModel.getRelease())
                .img(bookModel.getImg().getBytes(StandardCharsets.UTF_8))
                .amount(bookModel.getAmount())
                .publisher(publisherRepository.findPublisherByName(bookModel.getPublisherName()).orElseThrow(() -> new EBookException("not found")))
                .author(authorRepository.findAuthorByFullName(bookModel.getAuthorName()).orElseThrow(() -> new EBookException("not found")))
                .category(categoryRepository.findCategoryByName(bookModel.getCategoryName()).orElseThrow(() -> new EBookException("not found")))
                .description(bookModel.getDescription())
                .build();
    }

    @Override
    public List<BookModel> getAll(String sorting, Long filterByCategory, Long filterByAuthor, String search) {
        if (sorting != null){
            switch (sorting){
                case "name":
                    return bookRepository.getBooksSortedByName()
                            .stream().map(book -> toModel(book))
                            .collect(Collectors.toList());
                case "nameDesc":
                    return bookRepository.getBooksSortedByNameDesc()
                            .stream().map(book -> toModel(book))
                            .collect(Collectors.toList());
                case "category":
                    bookRepository.getBooksSortedByCategoryName()
                            .stream().map(book -> toModel(book))
                            .collect(Collectors.toList());
                case "categoryDesc":
                    bookRepository.getBooksSortedByCategoryNameDesc()
                            .stream().map(book -> toModel(book))
                            .collect(Collectors.toList());
                case "authorName":
                    bookRepository.getBooksSortedByAuthorName()
                            .stream().map(book -> toModel(book))
                            .collect(Collectors.toList());
                case "authorNameDesc":
                    bookRepository.getBooksSortedByAuthorNameDesc()
                            .stream().map(book -> toModel(book))
                            .collect(Collectors.toList());
            }
        }
        if (filterByCategory != 0){
            return bookRepository.getBooksFilteredByCategory(filterByCategory).stream()
                    .map(book -> toModel(book)).collect(Collectors.toList());
        }
        if (filterByAuthor != 0){
            return bookRepository.getBooksFilteredByAuthor(filterByAuthor).stream()
                    .map(book -> toModel(book)).collect(Collectors.toList());
        }
        return getAll();
    }
}
