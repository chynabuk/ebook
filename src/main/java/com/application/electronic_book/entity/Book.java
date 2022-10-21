package com.application.electronic_book.entity;

import com.sun.mail.iap.ByteArray;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book extends BaseEntity{
    @Column
    private String name;

    @Column
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate release;

    @Column
    private String description;

    @Column
    private Integer amount;

    @Column
    private byte[] img;

    @OneToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "book")
    private List<Order> orders;
}