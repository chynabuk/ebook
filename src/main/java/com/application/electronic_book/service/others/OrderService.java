package com.application.electronic_book.service.others;

import com.application.electronic_book.entity.Order;
import com.application.electronic_book.model.others.OrderModel;
import com.application.electronic_book.model.user.UserModel;
import com.application.electronic_book.service.basic_interfaces.Create;
import com.application.electronic_book.service.basic_interfaces.Read;
import com.application.electronic_book.service.basic_interfaces.ToModelConverter;

public interface OrderService extends Create<OrderModel, OrderModel>, ToModelConverter<OrderModel, Order>, Read<OrderModel, Order> {
    OrderModel confirmOrder(String token, Integer dayAmount);
    Order getByToken(String token);
}