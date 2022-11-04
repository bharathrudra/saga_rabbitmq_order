package com.bharath.rabbitmq.demo.service;

import com.bharath.rabbitmq.demo.dto.Order;

public interface IOrderService {

    void orderUpdateMessageFromQueue(Order order);

    String createOrder(Order order) ;

    void updateOrder(Order order);

    String getOrderStatusFromQueue();
}
