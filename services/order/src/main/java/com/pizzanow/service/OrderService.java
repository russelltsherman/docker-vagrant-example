package com.pizzanow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.pizzanow.config.MessagingConfig;
import com.pizzanow.model.CrudOrder;
import com.pizzanow.repository.OrderRepository;

@Service
public class OrderService extends BaseService<CrudOrder> {

	@Autowired
	protected OrderRepository orderRepo;
	
	@Autowired
	protected OrderMessagingService orderMessagingService;
	
	@Override
	protected CrudRepository<CrudOrder, Long> getRepository() {
		return orderRepo;
	}

	@Override
	public CrudOrder create(CrudOrder order) {
		CrudOrder o = super.create(order);
		orderMessagingService.push(MessagingConfig.KitchenQueueName, o);
		return o;
	}
}
