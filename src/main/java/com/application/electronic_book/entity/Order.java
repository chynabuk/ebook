package com.application.electronic_book.entity;

import com.application.electronic_book.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity{
    @Column(name = "start_date_time")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime startDateTime;

    @Column(name = "end_date_time")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime endDateTime;

    @Column
    private String token;

    @Column(name = "token_expiration_date_time")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime tokenExpirationDateTime;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @PrePersist
    public void initStatus(){
        this.status = OrderStatus.IN_PROCESS;
    }
}