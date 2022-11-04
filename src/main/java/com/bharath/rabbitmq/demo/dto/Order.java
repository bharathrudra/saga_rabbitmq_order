package com.bharath.rabbitmq.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue
    private String orderId;
    @Column
    private String name;
    @Column
    private int qty;
    @Column
    private double price;
    @Column
    private String customerId;
    @Column
    private OrderStatus status;
    @Column
    private String message;
    @Column
    private String retailerName;
}
