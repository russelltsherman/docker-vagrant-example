package com.pizzanow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizzanow.model.CrudOrder;
import com.pizzanow.service.BaseService;
import com.pizzanow.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController extends BaseCrudController<CrudOrder> {
	
	@Autowired
	private OrderService orderService;

	@Override
	protected BaseService<CrudOrder> getService() {
		return orderService;
	}
}
