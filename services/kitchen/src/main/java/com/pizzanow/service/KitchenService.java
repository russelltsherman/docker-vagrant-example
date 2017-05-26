package com.pizzanow.service;

import java.util.Map;
import java.util.Queue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import com.pizzanow.client.OrderRestClient;
import com.pizzanow.config.MessagingConfig;
import com.pizzanow.enums.OrderStatus;
import com.pizzanow.model.Order;
import com.pizzanow.util.JsonUtil;

@Service
public class KitchenService implements QueueHandler<Order> {
	
	@Autowired
	protected OrderMessagingService orderMessagingService;
	
	@Autowired
	protected OrderRestClient restClient;
	
	protected Queue<Order> orderQueue = Queues.newConcurrentLinkedQueue();
	protected Map<Long, Order> bakingQueue = Maps.newConcurrentMap();

	@RabbitListener(queues = MessagingConfig.KitchenQueueName)
	public void receive(final String orderJson) {
		orderQueue.add(JsonUtil.parseAsOrder(orderJson));
    }
	
	public Long pendingSize() {
		return (long) orderQueue.size();
	}
	
	public Long processingSize() {
		return (long) bakingQueue.size();
	}

	public Order next() {
		Order o = orderQueue.poll();
		bakingQueue.put(o.getId(),  o);
		return o;
	}

	public void complete(Long id) {
		Order order = bakingQueue.remove(id);
		order.setOrderStatus(OrderStatus.Baked);
		restClient.update(id, order);
		orderMessagingService.push(MessagingConfig.DeliveryQueueName, order);
	}
}
