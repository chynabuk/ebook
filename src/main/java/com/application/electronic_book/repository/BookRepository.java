package com.application.electronic_book.repository;

import com.application.electronic_book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(
            nativeQuery = true,
            value = "select * from books order by name"
    )
    List<Book> getBooksSortedByName();

    @Query(
            nativeQuery = true,
            value = "select * from books order by name desc "
    )
    List<Book> getBooksSortedByNameDesc();

    @Query(
            nativeQuery = true,
            value = "select * from books inner join categories on category_id = category.id order by categories.name"
    )
    List<Book> getBooksSortedByCategoryName();

    @Query(
            nativeQuery = true,
            value = "select * from books inner join categories on category_id = categories.id order by categories.name desc "
    )
    List<Book> getBooksSortedByCategoryNameDesc();

    @Query(
            nativeQuery = true,
            value = "select * from books inner join authors on author_id = authors.id order by authors.fullName"
    )
    List<Book> getBooksSortedByAuthorName();

    @Query(
            nativeQuery = true,
            value = "select * from books inner join authors on author_id = authors.id order by authors.name desc "
    )
    List<Book> getBooksSortedByAuthorNameDesc();

    @Query(
            nativeQuery = true,
            value = "select * from books where category_id = :category_id"
    )
    List<Book> getBooksFilteredByCategory(@Param("category_id") Long categoryId);

    @Query(
            nativeQuery = true,
            value = "select * from books where author_id = :author_id"
    )
    List<Book> getBooksFilteredByAuthor(@Param("author_id") Long authorId);
}
