package com.pizzanow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizzanow.model.Order;
import com.pizzanow.service.DeliveryService;
import com.pizzanow.service.QueueHandler;

@RestController
@RequestMapping("/delivery")
public class DeliveryController extends BaseQueueController<Order> {
	@Autowired
	protected DeliveryService deliveryService;

	@Override
	public QueueHandler<Order> getQueueHandler() {
		return deliveryService;
	}
}
