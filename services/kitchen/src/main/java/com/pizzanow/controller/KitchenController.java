package com.pizzanow.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizzanow.model.Order;
import com.pizzanow.service.KitchenService;
import com.pizzanow.service.QueueHandler;

@RestController
@RequestMapping("/kitchen")
public class KitchenController extends BaseQueueController<Order> {
	@Autowired
	protected KitchenService kitchenService;
	
	@Override
	public QueueHandler<Order> getQueueHandler() {
		return kitchenService;
	}
}
