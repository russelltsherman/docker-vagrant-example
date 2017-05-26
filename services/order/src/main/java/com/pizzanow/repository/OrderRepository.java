package com.pizzanow.repository;

import org.springframework.data.repository.CrudRepository;

import com.pizzanow.model.CrudOrder;

public interface OrderRepository extends CrudRepository<CrudOrder, Long> {

}
