package com.pizzanow.service;

import java.util.Map;
import java.util.Queue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import com.pizzanow.client.OrderRestClient;
import com.pizzanow.config.MessagingConfig;
import com.pizzanow.enums.OrderStatus;
import com.pizzanow.model.Order;

@Service
public class DeliveryService implements QueueHandler<Order> {
	
	@Autowired
	protected RabbitTemplate rabbitTemplate;
	
	@Autowired
	protected OrderRestClient restClient;
	
	protected Queue<Order> orderQueue = Queues.newConcurrentLinkedQueue();
	protected Map<Long, Order> deliveryQueue = Maps.newConcurrentMap();

	@RabbitListener(queues = MessagingConfig.DeliveryQueueName)
    public void receiveOrder(final Order order) {
        orderQueue.add(order);
    }
	
	public Long pendingSize() {
		return (long) orderQueue.size();
	}
	
	public Long processingSize() {
		return (long) deliveryQueue.size();
	}

	public Order next() {
		Order o = orderQueue.poll();
		deliveryQueue.put(o.getId(),  o);
		return o;
	}

	public void complete(Long id) {
		Order order = deliveryQueue.remove(id);
		order.setOrderStatus(OrderStatus.Delivered);
		restClient.update(id, order);
	}
}
