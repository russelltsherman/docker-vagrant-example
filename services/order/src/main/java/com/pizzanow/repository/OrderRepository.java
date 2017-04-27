package com.pizzanow.repository;

import org.springframework.data.repository.CrudRepository;

import com.pizzanow.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
