package com.application.electronic_book.service.others.impl;

import com.application.electronic_book.entity.Book;
import com.application.electronic_book.entity.Order;
import com.application.electronic_book.entity.User;
import com.application.electronic_book.enums.OrderStatus;
import com.application.electronic_book.exception.EBookException;
import com.application.electronic_book.model.others.OrderModel;
import com.application.electronic_book.model.user.UserModel;
import com.application.electronic_book.repository.BookRepository;
import com.application.electronic_book.repository.OrderRepository;
import com.application.electronic_book.service.others.BookService;
import com.application.electronic_book.service.others.OrderService;
import com.application.electronic_book.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final BookService bookService;

    private final BookRepository bookRepository;

    @Override
    public OrderModel create(OrderModel orderModel) {
        Order order = new Order();
        User user = userService.getEntityById(orderModel.getUserId());
        Book book = bookService.getEntityById(orderModel.getBookId());
        if (book.getAmount() <= 0){
            throw new EBookException("You can't order, book is not available for now");
        }
        order.setUser(user);
        order.setBook(book);
        order.setStatus(OrderStatus.IN_PROCESS);
        order.setTokenExpirationDateTime(LocalDateTime.now().plusDays(2));
        order.setToken(String.valueOf(UUID.randomUUID()).substring(0, 6));

        book.setAmount(book.getAmount() - 1);

        bookRepository.save(book);

        orderRepository.save(order);

        orderModel.setToken(order.getToken());

        orderModel.setId(order.getId());

        return orderModel;
    }

    @Override
    public OrderModel confirmOrder(String token, Integer dayAmount) {
        Order order = getByToken(token);

        if (order.getStatus() == OrderStatus.IN_PROCESS){
            order.setStatus(OrderStatus.CONFIRMED);

            order.setStartDateTime(LocalDateTime.now());
            order.setEndDateTime(LocalDateTime.now().plusDays(dayAmount));

            orderRepository.save(order);
        }
        return null;
    }

    @Override
    public Order getByToken(String token) {
        return orderRepository.findOrderByToken(token).orElseThrow(()->new EBookException("order with token not found"));
    }

    @Override
    public OrderModel toModel(Order order) {
        return OrderModel.builder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .bookId(order.getBook().getId())
                .tokenExpirationDate(order.getTokenExpirationDateTime())
                .token(order.getToken())
                .dayAmount(Period.between(LocalDate.now(), order.getEndDateTime().toLocalDate()).getDays())
                .build();
    }

    @Override
    public Order getEntityById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EBookException("order with id:" + id + " was not found"));
    }

    @Override
    public OrderModel getById(Long id) {
        return toModel(getEntityById(id));
    }

    @Override
    public List<OrderModel> getAll() {
        List<Order> orders = orderRepository.findAll();

        for (Order order : orders){
            if (!order.getIsDeleted()){
                if (LocalDateTime.now().isBefore(order.getTokenExpirationDateTime())){
                    orders.add(order);
                }
                else order.setIsDeleted(true);
            }
        }
        return orders.stream().map(order -> toModel(order)).collect(Collectors.toList());
    }
}
