package com.pizzanow.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzanow.model.Order;
import com.pizzanow.util.JsonUtil;

@Service
public class OrderMessagingService implements MessagingService<Order> {

	@Autowired
	protected RabbitTemplate rabbitTemplate;
	
	@Override
	public void push(String destinationQueue, Order entity) {
		rabbitTemplate.convertAndSend(destinationQueue, JsonUtil.convertOrder(entity));
	}

}
