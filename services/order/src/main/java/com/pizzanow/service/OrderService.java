package com.pizzanow.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.pizzanow.config.MessagingConfig;
import com.pizzanow.model.Order;
import com.pizzanow.repository.OrderRepository;

@Service
public class OrderService extends BaseService<Order> {

	@Autowired
	protected OrderRepository orderRepo;
	
	@Autowired
	protected RabbitTemplate rabbitTemplate;
	
	@Override
	protected CrudRepository<Order, Long> getRepository() {
		return orderRepo;
	}

	@Override
	public Order create(Order order) {
		Order o = super.create(order);
		rabbitTemplate.convertAndSend(MessagingConfig.KitchenQueueName, o);
		return o;
	}
}
