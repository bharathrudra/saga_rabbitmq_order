package com.bharath.rabbitmq.demo.controller;

import com.bharath.rabbitmq.demo.dto.Order;
import com.bharath.rabbitmq.demo.service.IOrderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private IOrderService iOrderService;

    @PostMapping("/placeOrder")
    public String bookOrder(@RequestBody Order order) {
        order.setOrderId(UUID.randomUUID().toString());
        //restaurantservice
        //payment service
//        OrderStatus orderStatus = new OrderStatus(order, "PROCESS", "order placed succesfully in " + restaurantName);
//        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, orderStatus);

        return iOrderService.createOrder(order);
    }


    @GetMapping("/orderStatus")
    public String checkStatus(){
        return iOrderService.getOrderStatusFromQueue();
    }


    //test method for now
    @PostMapping("/get")
    public String getOrder() {
//        order.setOrderId(UUID.randomUUID().toString());
//        //restaurantservice
//        //payment service
//        OrderStatus orderStatus = new OrderStatus(order, "PROCESS", "order placed succesfully in " + restaurantName);
//        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, orderStatus);
        return "get Success !!";
    }
}
