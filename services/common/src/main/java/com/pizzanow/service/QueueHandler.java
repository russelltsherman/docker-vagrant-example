package com.pizzanow.service;

public interface QueueHandler<T> {

	public void receiveOrder(final T item);
	public Long pendingSize();
	public Long processingSize();
	public T next();
	public void complete(Long id);
}
