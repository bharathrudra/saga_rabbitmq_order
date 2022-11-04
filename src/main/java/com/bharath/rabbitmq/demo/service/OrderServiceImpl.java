package com.bharath.rabbitmq.demo.service;

import com.bharath.rabbitmq.demo.config.MessagingConfig;
import com.bharath.rabbitmq.demo.dto.Order;
import com.bharath.rabbitmq.demo.dto.OrderStatus;
import com.bharath.rabbitmq.demo.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements IOrderService{

    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    public static final String ORDER_VERIFICATION_IN_PROGRESS = "Order Verification IN PROGRESS";
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    //private KafkaTemplate<String, Order> kafkaTemplate;
    private RabbitTemplate rabbitTemplate;

    private Order receivedOrderFromQueue;

    @RabbitListener(queues = MessagingConfig.CUSTOMER_QUEUE)
    //@KafkaListener(topics = MessagingConfig.CUSTOMER_TOPIC, groupId = "customer_group")
    @Override
    public void orderUpdateMessageFromQueue(Order order) {
        receivedOrderFromQueue = order;
        logger.info(" #### receivedOrderFromQueue $$$$$ {}",receivedOrderFromQueue);
        updateOrder(order);
    }

    @Override
    public String createOrder(Order order)  {
     order.setStatus(OrderStatus.PENDING);
     order.setMessage(ORDER_VERIFICATION_IN_PROGRESS);
     orderRepository.save(order);
     logger.info(" ############# Message Order &&&&&&&&&&& {}",order);
     rabbitTemplate.convertAndSend(MessagingConfig.ORDER_QUEUE,order);
     //kafkaTemplate.send(MessagingConfig.ORDER_TOPIC,order);
     return order.getMessage();
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public String getOrderStatusFromQueue() {

        return receivedOrderFromQueue.getStatus()
                +"!! Message !!"+ receivedOrderFromQueue.getMessage();
    }
}
