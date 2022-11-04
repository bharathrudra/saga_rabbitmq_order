package com.bharath.rabbitmq.demo.repository;

import com.bharath.rabbitmq.demo.dto.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface OrderRepository extends CrudRepository<Order,String> {
}
